package com.nddr.community.mapper;
//user表映射，将github用户信息存储到数据库表USER中
import com.nddr.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into USER (NAME,ACCOUNT_ID,TOKEN,GMT_CREATE,GMT_MODIFY) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModefied})")
    void insert(User user);

}
