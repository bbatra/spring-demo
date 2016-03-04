package com.demo.controllers

import com.demo.model.Alert
import com.demo.model.Entity
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


        //alertService.save(alert)
//
   //     println Gson.newInstance().toJson( alert )
        temp.clear()
        for (Entity e:alertService.getAllAlerts())
            temp.add((Alert) e)

 //       println Gson.newInstance().toJson( temp )
        temp
    }
    @RequestMapping("/getAlert/{id}")
    def getAlert(@PathVariable("id") String alertId)
    {
        println "This bitch just hit me: " + alertId
        alertService.getAlertById(alertId)
    }

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
