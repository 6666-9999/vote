package com.cssl.mapper;

import com.cssl.pojo.Subject;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface SubjectMapper {

    Page<Subject> getPage(Map<String,Object> map);

    int add(Subject sub);

    int del(int sid);

    int getNewId();

    Subject getBySid(int sid);

    Subject getByOptionsAll(int sid);

    int update(Subject sub);
}
