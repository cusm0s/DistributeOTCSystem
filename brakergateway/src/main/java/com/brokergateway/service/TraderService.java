package com.brokergateway.service;

import com.brokergateway.model.Trader;
import com.brokergateway.repository.TraderRepository;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by homepppp on 2018/5/23.
 */
@Service
public class TraderService {
    @Autowired
    TraderRepository traderRepository;

    public JSONObject createUser(JSONObject obj){
        Trader trader = new Trader();
        trader.setBalance(0.0);
        trader.setUsername((String)obj.get("username"));
        trader.setPassword((String)obj.get("password"));
        traderRepository.saveAndFlush(trader);
        return JSONObject.fromObject(trader);
    }

    public JSONObject getUserByUsernameAndPwd(JSONObject obj){
        String username = (String) obj.get("username");
        String password = (String) obj.get("password");
        Trader trader = traderRepository.getTraderByUsernameAndPassword(username,password);
        if(trader == null){
            return null;
        }
        return JSONObject.fromObject(trader);
    }
}
