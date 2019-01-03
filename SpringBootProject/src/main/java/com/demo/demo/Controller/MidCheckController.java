package com.demo.demo.Controller;

import com.demo.demo.Entity.Project;
import com.demo.demo.Service.FileUtil;
import com.demo.demo.Service.MidCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MidCheckController {
    @Autowired
    MidCheckService midCheckService;


    /**
     * 中期检查：分页查询已立项的项目（完成
     * @return
     */
    @GetMapping(value = "/project/list")
    public String getProjectList(Model model,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "5") Integer size) {
        model.addAttribute("datas", midCheckService.getApprovedProject(page, size));
        return "/middle/all_project_list";
    }

    /**
     * 中期检查：教职工查询自己的项目（待测试
     * @param model
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/projects")
    public String getMyProjects(Model model,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "5") Integer size
                                ) {
        model.addAttribute("datas", midCheckService.getUserProject(page, size));
        return "/middle/my_projects";
    }

    /**
     * 中期检查：对已立项的项目设置实时间期限和项目应该上传的材料说明(完成
     * @param deadline
     * @param description
     * @param projectCode
     * @return String
     */
    @ResponseBody
    @RequestMapping(value = "/middle_set", method = RequestMethod.POST)
    public Map setDeadlineAndMaterialDescription(@RequestParam(value = "code") int projectCode,
                                                 @RequestParam(value = "deadline") String deadline,
                                                 @RequestParam(value = "comment") String description){
        boolean isSave = false;
        try {
            isSave = midCheckService.updateProject(projectCode, description, deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        map.put("msg", "OK");
        map.put("code", 1);
        //AJAX返回保存结果
        return map;
    }

    /**
     * 中期检查：上传中期检查材料（文件上传类待修改
     * @return String
     */
    @ResponseBody
    @RequestMapping(value = "/upload_material", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map uploadMaterial(@RequestParam("file") MultipartFile file,
                              @RequestParam("projectCode") String projectcode) {
        String pathName = "/upload";
        String fileName = file.getOriginalFilename();
        try{
            FileUtil.uploadFile(file.getBytes(),pathName,"/"+fileName);
        }catch (Exception e){
            e.printStackTrace();
        }
        midCheckService.saveMidAddress(Integer.parseInt(projectcode),pathName+fileName);
        Map a = new HashMap();
        a.put("msg","success");
        a.put("code",0);
        return a;
    }

    /**
     * 中期检查：下载中期检查材料（未完成
     */
    @ResponseBody
    @RequestMapping(value = "/download_material", method = RequestMethod.GET)
    public String downloadMaterial(@RequestParam("projectCode")int projectCode,HttpServletResponse response){
        String filePath = midCheckService.findMidAddress(projectCode);
        File file = new File(filePath);
        if(file.exists()){
            response.setContentType("application/force-download");
            String fileName = "MidCheckReport";
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            fileName = fileName+ suffixName;
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "success!";
    }

    /**
     * 查看已提交材料的项目（完成
     * @param model
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/findMidCheckProject")
    public String findMidProjectList(Model model,
                                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "limit", defaultValue = "5") Integer size){
        model.addAttribute("projects",midCheckService.findMidProject(page,size));
        return "/middle/mid_check_project_list";
    }

    /**
     * 中期检查：审核
     * @param
     */
    @ResponseBody
    @RequestMapping(value = "/set_status", method = RequestMethod.POST)
    public String setStatus(@RequestBody Map<String,Object> map) {
        midCheckService.setMidCheckResult(Integer.parseInt((String)map.get("projectcode")),Integer.parseInt((String)map.get("state")));
        return "success!";
    }
}
