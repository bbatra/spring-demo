package com.demo.services

import com.demo.model.Alert
import com.demo.model.GeneralRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.management.Notification

/**
 * Created by bharatbatra on 2/27/16.
 */
@Service
class AlertService {
    private GeneralRepository generalRepository

    @Autowired
    AlertService(GeneralRepository generalRepository)
    {
        this.generalRepository = generalRepository
    }

    void save(Alert alert)
    {

        if( alert.time == null )
            alert.time = Date.newInstance()
        generalRepository.save( alert )
    }
}
