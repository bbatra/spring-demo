package com.demo.controllers

import com.demo.services.MongoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by bharatbatra on 2/27/16.
 */
@Controller()
class TestController {

    MongoService mongoService

    @Autowired
    TestController(MongoService mongoService)
    {
        this.mongoService = mongoService
    }


    @RequestMapping("/")
    def index()
    {

        "index"
    }
}
