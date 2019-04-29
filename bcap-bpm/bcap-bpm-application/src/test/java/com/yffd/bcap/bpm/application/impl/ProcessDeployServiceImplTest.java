package com.yffd.bcap.bpm.application.impl;

import com.yffd.bcap.bpm.application.JunitBaseTest;
import com.yffd.bcap.bpm.application.service.impl.ProcessDeployServiceImpl;
import com.yffd.bcap.bpm.domain.vo.ProcessDeploymentSaveVo;
import com.yffd.bcap.bpm.domain.vo.ProcessDeploymentVo;
import com.yffd.bcap.common.core.page.PageParam;
import com.yffd.bcap.common.core.page.PageResult;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class ProcessDeployServiceImplTest extends JunitBaseTest {

    @Autowired
    private ProcessDeployServiceImpl processDeployServiceImpl;

    @Test
    public void findPageTest() {
        String deployName = "测试";
        String deployKey = "test";
        String deployCategory = "练习";
        int pageNum = 0;
        int pageSize = 5;
        PageResult<ProcessDeploymentVo> page = processDeployServiceImpl.findPage(deployName, deployKey, deployCategory, pageNum, pageSize);

        if (null != page) {
            List<ProcessDeploymentVo> list =  page.getRecordList();
            System.out.println("ID\t\t发布名称\t\t发布关键字\t\t发布分类\t\tBPMN名称\t\tPNG名称");
            for (ProcessDeploymentVo vo : list) {
                System.out.println(
                        vo.getDeployId()
                                + "\t\t" + vo.getDeployName()
                                + "\t\t" + vo.getDeployKey()
                                + "\t\t" + vo.getDeployCategroy()
                );
            }
            System.out.println("--------------------------------------");
            PageParam pageParam = page.getPageParam();
            System.out.println(
                    "总记录数：" + pageParam.getTotalRecord() + " 总页数：" + pageParam.getTotalPage() + " 当前页：" + pageParam.getPageNum() + " 每页条数：" + pageParam.getPageLimit()
            );
        }

    }

    @Test
    public void findByDeployIdTest() {
        String deployId = "15001";
        ProcessDeploymentVo vo = processDeployServiceImpl.findByDeployId(deployId);

        System.out.println("ID\t\t发布名称\t\t发布关键字\t\t发布分类\t\tBPMN名称\t\tPNG名称");
        System.out.println(
                vo.getDeployId()
                + "\t\t" + vo.getDeployName()
                + "\t\t" + vo.getDeployKey()
                + "\t\t" + vo.getDeployCategroy()
                );
    }

    @Test
    public void deployTest() {
        String name = null;
        name = "测试";
        String categroy = "练习";
        String keyWorld = "test";
        String bpmnFileName = null;
//        bpmnFileName = "diagrams/helloworld.bpmn";
        String dgrmFileName = "diagrams/helloworld.png";
        String zipFileName = null;
        zipFileName = "diagrams/helloworld.zip";

        ProcessDeploymentSaveVo vo = new ProcessDeploymentSaveVo();
        vo.setDeployName(name);
        vo.setDeployCategroy(categroy);
        vo.setDeployKey(keyWorld);
        vo.setBpmnFileName(bpmnFileName);
        vo.setDgrmFileName(dgrmFileName);
        vo.setZipFileName(zipFileName);
        String id = processDeployServiceImpl.deploy(vo);

        System.out.println("部署ID：" + id);
    }

    @Test
    public void deleteByDeployIdTest() {
        String deployId = "2501";
        boolean force = true;
        processDeployServiceImpl.deleteByDeployId(deployId, force);
    }

    @Test
    public void loadBpmnTest() throws IOException {
        String deployId = "15001";
        InputStream inputStream = processDeployServiceImpl.loadBpmn(deployId);

        //将图片生成到D盘的目录下
        File file = new File("D:/ddd/helloworld.bpmn");
        //将输入流的图片写到D盘下
        FileUtils.copyInputStreamToFile(inputStream, file);
    }

    @Test
    public void loadDgrmTest() throws IOException {
        String deployId = "15001";
        InputStream inputStream = processDeployServiceImpl.loadDgrm(deployId);

        //将图片生成到D盘的目录下
        File file = new File("D:/ddd/helloworld.png");
        //将输入流的图片写到D盘下
        FileUtils.copyInputStreamToFile(inputStream, file);
    }

}
