package com.demo.model

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

/**
 * Created by bharatbatra on 2/27/16.
 */
@Repository
class AlertRepository {
    private GeneralRepository generalRepository

    @Autowired
    AlertRepository(GeneralRepository generalRepository)
    {
        this.generalRepository = generalRepository
    }
}
