package com.kangde.collection.model;

import java.util.Date;
/**
 * 
  * @Description: 附件
  * @author lidengwen
  * @date 2016年6月14日 下午12:00:07
  *
 */
public class AttachmentModel {
	/**附件id */
    private String id;

    /**附件名称 */
    private String name;

    /**附件大小 */
    private Long size;

    /**附件路径 */
    private String path;

    private String isjunk;

    /**附件上传日期 */
    private Date date;

    /**附件类型 附件类型 案件：bankCase、信函：mes、外访：vis */
    private String type;

    /**外访记录表ID、案件表ID */
    private String fkId;

    /**文件类型  */
    private String fileType;
    /**文件后缀  */
    private String fileFormat;
    
    private String createEmpName;

    private Date createTime;

    private String modifyEmpName;

    private Date modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getIsjunk() {
        return isjunk;
    }

    public void setIsjunk(String isjunk) {
        this.isjunk = isjunk == null ? null : isjunk.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getFkId() {
        return fkId;
    }

    public void setFkId(String fkId) {
        this.fkId = fkId == null ? null : fkId.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	
	public String getCreateEmpName() {
		return createEmpName;
	}

	public void setCreateEmpName(String createEmpName) {
		this.createEmpName = createEmpName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifyEmpName() {
		return modifyEmpName;
	}

	public void setModifyEmpName(String modifyEmpName) {
		this.modifyEmpName = modifyEmpName;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "CaseAttachment [id=" + id + ", name=" + name + ", size=" + size + ", path=" + path + ", isjunk="
				+ isjunk + ", date=" + date + ", type=" + type + ", fkId=" + fkId + ", fileType=" + fileType
				+ ", fileFormat=" + fileFormat + "]";
	}
    
}