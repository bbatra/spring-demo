package com.demo.model

import com.mongodb.DBCollection
import com.mongodb.DBObject
import com.mongodb.WriteResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
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
        this.mongoTemplate = mongoTemplate
    }

    //CREATE/WRITE
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


    //READ/GET
    Entity getEntityById(Class entityClass, String uid)
    {
        Query query = new Query()
        query.addCriteria(Criteria.where("uid").is(uid))
        Entity e = (Entity) mongoTemplate.findOne(query, entityClass)
        e
    }

    Boolean entityWithIdExists(Class entityClass, String uid)
    {
        Query query = new Query()
        query.addCriteria(Criteria.where("uid").is(uid))
        mongoTemplate.exists(query, entityClass)
    }

    List<Entity> getAll(Class entityCLass)
    {
        mongoTemplate.findAll(entityCLass)
    }

    //UPDATE/ALTER
    Entity updateEntityById(Class entityClass, String uid)//How Will this work?
    {

        Query query = new Query()//how to build this query?
        query.addCriteria(Criteria.where("id").is(uid))
         mongoTemplate.findOne(query, entityClass)
    }

    //DELETE
    WriteResult deleteEntityById(Class entityClass, String uid)
    {
        Entity e
        Query query = new Query()
        query.addCriteria(Criteria.where("uid").is(uid))
        mongoTemplate.remove(query, entityClass)
    }

}
