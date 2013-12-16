/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.helper;

import com.google.gson.Gson;

/**
 *
 * @author al037294
 */
public class ParserJson {
    
    
    public String listJson(Object list){
        Gson oGson = new Gson();
        String strJson = oGson.toJson(list);
        return strJson;
    }
}
