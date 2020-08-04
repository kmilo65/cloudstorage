package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.data.File;
import com.udacity.jwdnd.course1.cloudstorage.data.Note;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File getFile(Integer fileid);


    @Insert("INSERT INTO FILES (filename,contenttype,filesize,filedata,userid) VALUES(#{filename}, #{contenttype}, #{filesize}, #{filedata}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);


    @Update("UPDATE FILES SET filename=#{filename}, contenttype=#{contenttype}, filesize=#{filesize}, filedata=#{filedata}, userid=#{userid} WHERE fileId=#{fileId}")
    void updateFile(File file);

    @Delete("DELETE FROM FILES WHERE fileId=#{fileId}")
    void deleteFile(Integer fileId);
}


