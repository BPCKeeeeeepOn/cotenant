package com.youyu.cotenant.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.youyu.cotenant.utils.dto.AddressLocationDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

@Slf4j
public class CommonUtils {

    public static int generateWorkerId() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            String hostname = address.getHostAddress();
            if (StringUtils.isNotBlank(hostname)) {
                String bits = new BigInteger(hostname.getBytes()).toString(2);
                BigInteger rightPart = new BigInteger(StringUtils.right(bits, 10), 2);
                return rightPart.intValue();
            }
        } catch (UnknownHostException e) {
            log.error("unknown host, Reason: ", e);
        }
        return 0;
    }


    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, String> sortMap = new TreeMap<String, String>(
                new MapKeyComparator());

        sortMap.putAll(map);

        return map;
    }

    /**
     * 随机生成6位数
     *
     * @return
     */
    public static String randomNum() {
        int num = (int) ((Math.random() * 9 + 1) * 100000);
        return String.valueOf(num);
    }

    /**
     * @param number 需要保留两位的数
     * @return
     */
    public static double doubleFormat(double number) {
        // 将double类型转为BigDecimal
        BigDecimal bigDecimal = new BigDecimal(number);
        // 保留两位小数,并且四舍五入
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * @param number 需要保留五位的数
     * @return
     */
    public static double doubleFormat5(double number) {
        // 将double类型转为BigDecimal
        BigDecimal bigDecimal = new BigDecimal(number);
        // 保留两位小数,并且四舍五入
        return bigDecimal.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 获取高德地图对应的地址坐标点
     *
     * @param address
     * @return
     */
    public static AddressLocationDTO geoMapCode(String address) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://restapi.amap.com/v3/geocode/geo?key=51c08d9ff40d1aee8d256eb01a7cf90f&address=%s";
        ResponseEntity<JsonNode> forEntity = restTemplate.getForEntity(String.format(url, address), JsonNode.class);
        JsonNode body = forEntity.getBody();
        int status = body.get("status").asInt();
        int count = body.get("count").asInt();
        if (Objects.equals(forEntity.getStatusCodeValue(), HttpStatus.OK.value())
                && Objects.equals(status, NumberUtils.INTEGER_ONE)
                && count > 0) {
            JsonNode geocodes = body.get("geocodes");
            if (geocodes.isArray()) {
                JsonNode addressNode = geocodes.get(0);
                AddressLocationDTO addressLocationDTO = AddressLocationDTO.builder()
                        .formattedAddress(addressNode.get("formatted_address").asText())
                        .country(addressNode.get("country").asText())
                        .province(addressNode.get("province").asText())
                        .city(addressNode.get("city").asText())
                        .district(addressNode.get("district").asText())
                        .location(addressNode.get("location").asText()).build();
                return addressLocationDTO;
            }
        }
        return null;
    }
}
