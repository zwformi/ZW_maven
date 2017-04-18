package com.zw.entity;

public class File {
  private Integer id;  //文件主键id
  private Integer recordid;	//文件记录id
  private String  filepath; //文件路径
  private String  name; //文件路径
  private Integer cover; //是否默认文件
  private Integer type;  //文件类型0图片1文本2音频3视频4压缩文件
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getRecordid() {
	return recordid;
}
public void setRecordid(Integer recordid) {
	this.recordid = recordid;
}
public String getFilepath() {
	return filepath; 
}
public void setFilepath(String filepath) {
	this.filepath = filepath;
}
public Integer getCover() {
	return cover;
}
public void setCover(Integer cover) {
	this.cover = cover;
}
public Integer getType() {
	return type;
}
public void setType(Integer type) {
	this.type = type;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}	
	
	
}
