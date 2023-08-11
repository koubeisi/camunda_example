package com.koubeisi.security.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.repository.DiagramLayout;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.TransitionInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.model.bpmn.instance.Definitions;
import org.camunda.bpm.model.xml.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author koubeisi
 * @since 2023/8/11 10:27
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class ProjectPlanController {

    private final IdentityService identityService;
    private final RuntimeService runtimeService;
    private final RepositoryService repositoryService;
    private final TaskService taskService;


    /**
     * 查询流程
     */
    @GetMapping("/projectPlan/process")
    public void listProcess(){
        var processDefinitionList = repositoryService.createProcessDefinitionQuery().processDefinitionKey("Process_test1").list();
        var processDefinitionList1 = repositoryService.createProcessDefinitionQuery().processDefinitionKey("Process_test1").active().list();
        var processDefinition = repositoryService.getProcessDefinition("Process_test1:1:7320d6a5-37ee-11ee-aa29-00155d2aa086");
        log.info("{}", processDefinition);

    }

    /**
     * 查询流程中各个节点
     */
    @GetMapping("/projectPlan/processActivity")
    public void listProcessActivity(){
        var bpmnModelInstance = repositoryService.getBpmnModelInstance("Process_test1:1:7320d6a5-37ee-11ee-aa29-00155d2aa086");
//        bpmnModelInstance.get
        var definitions = bpmnModelInstance.getDefinitions();
        var model = bpmnModelInstance.getModel();
        log.info("{}", bpmnModelInstance);
    }


    /**
     * 查询流程图
     * TODO
     */
    @GetMapping("/projectPlan/processDiagram")
    public void listProcessDiagram(){
        var processDefinition = repositoryService.getProcessDefinition("Process_test1:1:7320d6a5-37ee-11ee-aa29-00155d2aa086");
/*        var diagramResourceName = processDefinition.getDiagramResourceName();
        var diagramResource = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), diagramResourceName);
        log.info("{}", diagramResource);*/
        var diagramLayout = repositoryService.getProcessDiagramLayout(processDefinition.getId());
        log.info("{}", diagramLayout);
    }

    /**
     * 开启流程
     */
    @PostMapping("/projectPlan/process")
    public void startProcess(){
        identityService.setAuthenticatedUserId("koubeisi");
        var processInstance = runtimeService.startProcessInstanceByKey("Process_test1");
        log.info("{}", processInstance.getProcessDefinitionId());
        log.info("{}", processInstance.getCaseInstanceId());
        log.info("{}", processInstance.getRootProcessInstanceId());
        log.info("{}", processInstance.getBusinessKey());
    }

    /**
     * 查询流程实例
     */
    @GetMapping("/projectPlan/processInstance")
    public void listProcessInstance(){
        var processInstanceList = runtimeService.createProcessInstanceQuery().processDefinitionKey("Process_test1").list();
        var processInstanceList1 = runtimeService.createProcessInstanceQuery().processDefinitionKey("Process_test1").processInstanceId("2813306c-37f7-11ee-9c6b-00155d2aa086").list();
        log.info("{}", processInstanceList);
        var activityInstance = runtimeService.getActivityInstance("2813306c-37f7-11ee-9c6b-00155d2aa086");
        log.info("{}", activityInstance);
        var childActivityInstances = activityInstance.getChildActivityInstances();
        var childTransitionInstances = activityInstance.getChildTransitionInstances();
        log.info("{}", childActivityInstances);
    }


    /**
     * 查询进行中的任务
     */
    @GetMapping("/projectPlan/task")
    public List<Task> listTask(){
        var tasks1 = taskService.createTaskQuery().caseInstanceId("2813306c-37f7-11ee-9c6b-00155d2aa086").list();
        var tasks = taskService.createTaskQuery().list();
        log.info("{}", tasks);
        return tasks;
    }
}
