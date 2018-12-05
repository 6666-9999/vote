package com.cssl.controller;

import com.cssl.pojo.Item;
import com.cssl.pojo.Options;
import com.cssl.pojo.Subject;
import com.cssl.pojo.Users;
import com.cssl.service.ItemService;
import com.cssl.service.OptionsService;
import com.cssl.service.SubjectService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class SubjectController {

    @Autowired
    private SubjectService subService;

    @Autowired
    private OptionsService optService;

    @Autowired
    private ItemService itemService;

    /**
     * 查询分页信息
     * @param map
     * @param mo
     * @return
     */
    @RequestMapping("/page")
    public String load(@RequestParam Map<String,Object> map, Model mo){
        Page<Subject> page=subService.getPage(map);
        mo.addAttribute("pg",page);
        return "votelist";
    }

    /**
     * 添加新投票
     * @param sub
     * @param content
     * @return
     */
    @RequestMapping("/addSub")
    public String addSub(Subject sub,@RequestParam String[] content){
        if(subService.addSubejct(sub)){
            int sid=subService.getNewId();
            for (String s:content) {
                Options op=new Options();
                op.setContent(s);
                op.setOsid(sid);
                optService.addOptions(op);
            }
        }
        return "forward:/page";
    }

    /**
     * 查看投票占比
     * @param sid
     * @param mo
     * @return
     */
    @RequestMapping("/votes")
    public String vote(int sid,Model mo){
        Subject sub=subService.getSubBySid(sid);
        mo.addAttribute("sub",sub);
        List<Options> list=optService.getByOsid(sub.getSid());
        for (Options op:list) {
            int count=itemService.getByOidCount(op.getOid());
            op.setVoteCount(count);
        }
        mo.addAttribute("list",list);
        return "voteview";
    }

    /**
     * 显示投票选项
     * @param sid
     * @param mo
     * @return
     */
    @RequestMapping("/addVote")
    public String addVote(int sid,Model mo){
        Subject sub=subService.getSubBySid(sid);
        mo.addAttribute("sub",sub);
        List<Options> list=optService.getByOsid(sub.getSid());
        mo.addAttribute("list",list);
        return "vote";
    }

    /**
     * 投票
     * @param options
     * @param item
     * @param session
     * @return
     */
    @RequestMapping("/savaVote")
    public String savaVote(@RequestParam int[] options, Item item,int sid, HttpSession session){
        Users users=(Users)session.getAttribute("user");
        if(users!=null){
            int uid=users.getUid();
            if(itemService.isVote(uid,sid)>0){
                return "qxerror";
            }else{
                item.setUid(uid);
                for (int oid:options) {
                    item.setOid(oid);
                    itemService.savaItem(item);
                }
            }

        }
        return "forward:/page";
    }

    /**
     * 跳转维护页面
     * @param map
     * @param mo
     * @return
     */
    @RequestMapping("/maintain")
    public String maintain(@RequestParam Map<String,Object> map, Model mo){
        Page<Subject> page=subService.getPage(map);
        mo.addAttribute("pg",page);
        return "forward:/maintainPage";
    }

    /**
     * 跳转修改页面
     * @param sid
     * @param mo
     * @return
     */
    @RequestMapping("/updateVote")
    public String update(int sid,Model mo){
        Subject sub=subService.getByOptions(sid);
        mo.addAttribute("sub",sub);
        return "update";
    }

    /**
     * 删除投票信息
     * @param sid
     * @return
     */
    @RequestMapping("/del")
    public String del(int sid){
        if(subService.del(sid)){
            if(optService.del(sid)){
                if(itemService.del(sid)){
                    return "forward:/page";
                }
            }
        }
        return "forward:/maintain";
    }

    /**
     * 修改投票信息
     * @param sub
     * @return
     */
    @RequestMapping("/modifier")
    public String modifier(Subject sub){
        if(subService.updateVote(sub))
            return "forward:/page";
        return "forward:/updateVote?sid="+sub.getSid();
    }

    /**
     * 跳转添加页面
     * @return
     */
    @RequestMapping("/addView")
    public String addView(){
        return "add";
    }


    @RequestMapping("/qx")
    public String qxError(String ym,HttpSession session){
        Users user=(Users)session.getAttribute("user");
        if(user.getIsAdmin()==0){
            if("maintain".equals(ym)){
                return "forward:/maintain";
            }else if("addView".equals(ym)){
                return "forward:/addView";
            }
        }
        return "xserror";
    }

    @RequestMapping("/maintainPage")
    public String maintainPage(@RequestParam Map<String,Object> map, Model mo){
        Page<Subject> page=subService.getPage(map);
        mo.addAttribute("pg",page);
        return "voteMaintain";
    }
}
