package com.brctl.serial.kryo;

import com.brctl.serial.bean.Person;
import com.esotericsoftware.kryo.Kryo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duanxiaoxing
 * @created 2018/5/31
 */
@Slf4j
public class KryoCopy {

    public static void main(String[] args) {
        Kryo kryo = new Kryo();
        Engineer engineer = new Engineer();
        engineer.setName("Tom");
        engineer.setAge(25);
        engineer.setCareer("engineer");
        engineer.setPhone("15201726287");
        List<String> skills = new ArrayList<>();
        skills.add("math");
        skills.add("programming");
        skills.add("system");
        skills.add("algorithm");
        engineer.setSkills(skills);
        // deep copy
        Engineer deepCopyEngineer = kryo.copy(engineer);
        log.info("deep copy result: {}, is skills equal: {}",
                deepCopyEngineer, engineer.getSkills() == deepCopyEngineer.getSkills());
        // shallow copy
        Engineer shallowCopyEngineer = kryo.copyShallow(engineer);
        log.info("shallow copy result: {}, is skills equal: {}",
                shallowCopyEngineer, engineer.getSkills() == shallowCopyEngineer.getSkills());
    }

    @Data
    static class Engineer extends Person {
        private List<String> skills;
    }

}
