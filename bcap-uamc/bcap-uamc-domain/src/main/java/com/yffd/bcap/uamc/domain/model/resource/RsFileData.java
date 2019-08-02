package com.yffd.bcap.uamc.domain.model.resource;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="uamc_rs_file")
public class RsFileData extends ResourceData {
    private static final long serialVersionUID = -5303092986997212899L;
    @Id
    private String fileId;//文件ID
    private String fileName;//文件名称
    private String filePosition;//文件位置

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePosition() {
        return filePosition;
    }

    public void setFilePosition(String filePosition) {
        this.filePosition = filePosition;
    }
}
