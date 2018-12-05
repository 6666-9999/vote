package com.cssl.service.impl;

import com.cssl.mapper.OptionsMapper;
import com.cssl.mapper.SubjectMapper;
import com.cssl.pojo.Options;
import com.cssl.pojo.Subject;
import com.cssl.service.SubjectService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectMapper sm;

    @Autowired
    private OptionsMapper om;

    @Override
    public Page<Subject> getPage(Map<String, Object> map) {
        int pageIndex=1;
        if(map!=null&&map.size()>0){
            String index=(String)map.get("pageIndex");
            if(index!=null&&!("").equals(index))
              pageIndex=Integer.valueOf(index);
        }
        Page<Subject> page=PageHelper.startPage(pageIndex,3);
        sm.getPage(map);
        return page;
    }

    @Override
    public boolean addSubejct(Subject sub) {
        int row=sm.add(sub);
        if(row>0)
            return true;
        return false;
    }

    @Override
    public int getNewId() {
        return sm.getNewId();
    }

    @Override
    public Subject getSubBySid(int sid) {
        return sm.getBySid(sid);
    }

    @Override
    public Subject getByOptions(int sid) {
        return sm.getByOptionsAll(sid);
    }

    @Override
    public boolean del(int sid) {
        if(sm.del(sid)>0)
            return true;
        return false;
    }



    @Override
    public boolean updateVote(Subject sub){
        System.out.println("size:"+sub.getOps().size());
        if(sm.update(sub)>0){
            List<Options> ps=om.getBySid(sub.getSid());
            System.out.println("ps.size:"+ps.size());
            if(ps.size()==sub.getOps().size()){
                for (int i=0;i<sub.getOps().size();i++) {
                    Options op = sub.getOps().get(i);
                    if (op.getContent() == null) {
                        om.del(ps.get(i).getOid());
                    } else {
                        om.update(op);
                    }
                }
            }else if(ps.size()<sub.getOps().size()){
                for (int i=0;i<sub.getOps().size();i++){
                    if(i<ps.size()){
                        Options op = sub.getOps().get(i);
                        if (op.getContent() == null) {
                            om.del(ps.get(i).getOid());
                        } else {
                            om.update(op);
                        }
                    }else{
                        Options opt=sub.getOps().get(i);
                        opt.setOsid(sub.getSid());
                        om.add(opt);
                    }
                }
            }else if(ps.size()>sub.getOps().size()){
                for (int i=0;i<ps.size();i++){
                    if(i<sub.getOps().size()){
                        om.update(sub.getOps().get(i));
                    }else{
                        om.del(ps.get(i).getOid());
                    }
                }
            }
            return true;
        }
        return false;
    }
}
