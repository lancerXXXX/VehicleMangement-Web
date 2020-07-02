package com.lancer.backend.controller;

import java.util.Date;
import java.util.List;

import com.lancer.backend.entity.Driver;
import com.lancer.backend.service.Impl.DriverServImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class DriverInfoController {

    @Autowired
    private DriverServImpl driverServ = new DriverServImpl();

    /**
     * 根据参数获取司机信息
     * @param info
     * @return
     */
    @GetMapping("/driverinfo")
    public List<Driver> findAll(@RequestParam(name = "query") String info) {
        if (info.length()>0) {
            return driverServ.findByDriverNameLike("%"+info+"%");
        } else {
            return driverServ.findAll();
        }
    }

    /**
     * 添加司机
     * @param entity
     * @return
     */
    @PostMapping(value = "/driverinfo/adduser")
    public String addOne(@RequestBody Driver entity) {
        entity.setDriverId(2);
        entity.setEntryDate(new java.sql.Date(new Date().getTime()));
        if (driverServ.AddOne(entity) != null) {
            return "成功";
        } else {
            return "失败";
        }
    }

    /**
     * 根据id获取司机信息
     * @param param
     * @return
     */
    @GetMapping(value = "/driverinfo/edit/{id}")
    public Driver getOneforEdit(@PathVariable("id") Long param) {
        if (param == null) {
            return null;
        } else {
            return driverServ.getOnebyId(param);
        }
    }

    /**
     * 编辑司机信息
     * @param entity
     * @return
     */
    @PostMapping(value = "/driverinfo/editinfo")
    public String updateDriverInfo(@RequestBody Driver entity) {
        if (driverServ.update(entity) != null) {
            return "成功";
        } else {
            return "失败";
        }
    }

    /**
     * 删除司机
     * @param param
     * @return
     */
    @DeleteMapping(value = "/driverinfo/delete/{id}")
    public String deleteDriver(@PathVariable("id") Long param) {
        if (driverServ.delete(param)) {
            return "成功";
        } else {
            return "失败";
        }
    }

}