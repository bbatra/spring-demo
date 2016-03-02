package com.demo.model

import org.springframework.data.annotation.Id


/**
 * Created by bharatbatra on 2/27/16.
 */
class Alert extends Entity
{
    String title
    Date time = new Date()
}
