package com.demo.model

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository

/**
 * Created by bharatbatra on 2/27/16.
 */
@Repository
class GeneralRepository {
    private MongoTemplate mongoTemplate

    @Autowired
    GeneralRepository(MongoTemplate mongoTemplate)
    {

    }

    void save (def obj)
    {
        if (obj instanceof Entity)
        {
            Entity entity = (Entity) obj
            if(entity.uid == null)
                entity.uid = UUID.randomUUID().toString()
        }

        mongoTemplate.save(obj)
    }
}
