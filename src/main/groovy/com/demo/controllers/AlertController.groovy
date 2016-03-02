package com.demo.controllers

import com.demo.model.Alert
import com.demo.services.AlertService
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.json.GsonJsonParser
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

/**
 * Created by bharatbatra on 2/27/16.
 */
@RestController
@RequestMapping("/alert")
class AlertController
{
    private AlertService alertService

    @Autowired
    AlertController(AlertService alertService)
    {
        this.alertService = alertService
    }

    @RequestMapping(value = "/notification", method = RequestMethod.POST    )
    List<Alert> receivedAlert (@RequestBody Alert alert)
    {

        if( alert.time == null )
            alert.time = Date.newInstance()

        println "Alert Received : ${alert.time} ${alert.title}"

        def temp = [alert]

        println temp.class.name

        println Gson.newInstance().toJson( alert )

        [alert]
    }
//    @RequestMapping("/{id}")
//    def getAlert(@PathVariable("id") String alertId)
//    {
//        // this is not returning anything except the println call
//
////        println("ALERTID")
////        println(alertId)
//    }

    @RequestMapping("/hit")
    String sendAlert()
    {
        println "took a hit"
        "Took A Single Hit"
    }

    @RequestMapping("/getAlert")
    Alert alert()
    {
        new Alert( title:"Test Alert", time: Date.newInstance() )
    }
}
