package com.shiropure.api;

import com.shiropure.Model.Gear.*;
import com.shiropure.utils.DateUtil;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.shiropure.utils.IOUtil.*;
import static com.shiropure.utils.SplatoonApiUtil.openNode;
import static com.shiropure.utils.SplatoonApiUtil.openNodeList;

public class SplatoonGearApi {
    static String gearApi = "https://splatoon3.ink/data/gear.json";

    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static Shop GearShop() throws IOException {
        OffsetDateTime currentTime = OffsetDateTime.now();
        String filePath = "./Caches/gear"+currentTime.getHour()+".data";
        Map<String, Object> dataMap = getCacheOrDownloadfromApi(filePath,gearApi);
        dataMap = openNode(dataMap,"gesotown");
        PickupBrand pickupBrand = ReadPickupBrand(dataMap);
        GearOffer[] limitedGears = ReadGearOffers(dataMap);
        return new Shop(pickupBrand,limitedGears);
    }
    public static  PickupBrand ReadPickupBrand(Map<String,Object> dataMap)
    {
        Map<String, Object> pickupBrand = openNode(dataMap,"pickupBrand");
        String pickupBrandImageUrl = (String) openNode(pickupBrand,"image").get("url");
        String brandName = (String) openNode(pickupBrand,"brand").get("name");
        String brandGearPowerName = (String) openNode(openNode(pickupBrand,"brand"),"usualGearPower").get("name");
        String brandGearPowerDesc = (String) openNode(openNode(pickupBrand,"brand"),"usualGearPower").get("desc");
        String brandGearPowerImageUrl = (String) openNode(openNode(openNode(pickupBrand,"brand"),"usualGearPower"),"image").get("url");
        OffsetDateTime saleEndTime = DateUtil.dateFormatter(pickupBrand.get("saleEndTime").toString(),dateTimeFormatter);
        GearOffer[] brandGears = ReadGearBlock(openNodeList(pickupBrand,"brandGears"));
        String NextBrandName = openNode(pickupBrand,"nextBrand").get("name").toString();
        String NextBrandImageUrl = openNode(openNode(pickupBrand,"nextBrand"),"image").get("url").toString();
        return new PickupBrand(pickupBrandImageUrl,brandName,brandGearPowerName,brandGearPowerDesc,brandGearPowerImageUrl,saleEndTime,NextBrandName,NextBrandImageUrl,brandGears);
    }
    public static GearOffer[] ReadGearBlock(List<Map<String, Object>> data)
    {
        List<GearOffer> out = new ArrayList<>();
        for (Map<String, Object> node:data) {
            OffsetDateTime saleEndTime = DateUtil.dateFormatter(node.get("saleEndTime").toString(),dateTimeFormatter);
            double price = Double.parseDouble(node.get("price").toString());
            Gear gear = ReadGear(openNode(node,"gear"));
            out.add(new GearOffer(saleEndTime,price,gear));
        }
        return out.toArray(new GearOffer[0]);
    }
    public static Gear ReadGear(Map<String, Object> node)
    {
        String gearName = (String) node.get("name");
        GearPower primaryGearPower = ReadGearPower(openNode(node,"primaryGearPower"));
        GearPower[] additionalGearPowers = ReadGearPowerList(openNodeList(node,"additionalGearPowers"));
        String imageUrl = openNode(node,"image").get("url").toString();
        Brand brand = ReadBrand(node);
        return new Gear(gearName,primaryGearPower,additionalGearPowers,imageUrl,brand);
    }

    private static Brand ReadBrand(Map<String, Object> node) {
        String name = openNode(node,"brand").get("name").toString();
        String imageUrl = openNode(openNode(node,"brand"),"image").get("url").toString();
        return new Brand(name,imageUrl);
    }

    private static GearPower[] ReadGearPowerList(List<Map<String, Object>> additionalGearPowers) {
        List<GearPower> gearPowers = new ArrayList<>();
        for (Map<String, Object> additionalGearPower : additionalGearPowers) {
            gearPowers.add(ReadGearPower(additionalGearPower));
        }
        return gearPowers.toArray(new GearPower[0]);
    }

    private static GearPower ReadGearPower(Map<String, Object> primaryGearPower) {
        String name = primaryGearPower.get("name").toString();
        String imageUrl = openNode(primaryGearPower,"image").get("url").toString();
        return new GearPower(name,imageUrl);
    }

    public static GearOffer[] ReadGearOffers(Map<String,Object> dataMap)
    {
        return ReadGearBlock(openNodeList(dataMap,"limitedGears"));
    }

}
