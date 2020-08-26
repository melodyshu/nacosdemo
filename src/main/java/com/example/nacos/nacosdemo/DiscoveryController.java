package com.example.nacos.nacosdemo;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: huangzhimao
 * @Date: 2020-08-26
 */
@RestController
@RequestMapping("/discovery")
public class DiscoveryController {

  @NacosInjected
  private NamingService namingService;

  @Value("${server.port}")
  private int serverPort;

  @Value("${spring.application.name}")
  private String applicationName;

  @GetMapping(value = "/get")
  public List<Instance> get(@RequestParam String serviceName) throws NacosException {
    return namingService.getAllInstances(serviceName);
  }

  @PostConstruct
  public void registerInstance() throws NacosException {
    namingService.registerInstance(applicationName, "127.0.0.1", serverPort);
  }
}
