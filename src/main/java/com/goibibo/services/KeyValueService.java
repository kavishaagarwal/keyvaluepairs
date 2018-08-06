package com.goibibo.services;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class KeyValueService {
    Map<String, Integer> map = new HashMap();

    public boolean uploadData() {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            String path = "/corpus.csv";
            br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path)));
            br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] data = line.split(cvsSplitBy);
                log.info("Key: {} Value: {}", data[0], data[1]);

                map.put(data[0], Integer.valueOf(data[1]));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public JSONObject getKeyValuePair(int value) {
        JSONObject response = new JSONObject();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (value == entry.getValue()) {
                response.put("key", entry.getKey());
                response.put("value", entry.getValue());

                break;
            }
        }
        return response;
    }
}
