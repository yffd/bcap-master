package com.yffd.bcap.bpm.domain.repository;

import com.yffd.bcap.bpm.domain.model.deploy.ProcessDeploy;

public interface ProcessDeployRepository {

    void add(ProcessDeploy processDeployEntity);

    void remove(String id);
}
