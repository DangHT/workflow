package me.danght.workflow.scheduler.dao.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.quarkus.redis.client.RedisClient;
import me.danght.workflow.common.serialization.BaseMapper;
import me.danght.workflow.scheduler.element.BpmnModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BpmnModelCacheDao {
    private static final String KEY_PATTERN = "BpmnModel:%s"; // wProcessDefinition:流程定义主键

    //@Resource(name = "redisTemplate")
    //@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    //private ValueOperations<String, Object> operations;

    @Inject
    RedisClient redisClient;

    private static String buildKey(String id) {
        return String.format(KEY_PATTERN, id);
    }

    public BpmnModel get(String id) {
        String key = buildKey(id);
        String value = redisClient.get(key).toString();
        BpmnModel bpmnModel = null;
        try {
            bpmnModel = BaseMapper.getObjectMapper().readValue(value, BpmnModel.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return bpmnModel;
    }

    public void set(String id, BpmnModel model) {
        String key = buildKey(id);
        String value = null;
        try {
            value = BaseMapper.getObjectMapper().writeValueAsString(model);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (value != null) redisClient.append(key, value);
    }
}
