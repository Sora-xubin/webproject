package com.demo.demo.Controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.demo.demo.Entity.Project;
import com.demo.demo.Service.FileUtil;
import com.demo.demo.Service.LastCheckService;
import com.demo.demo.Service.MidCheckService;
@Controller
public class LastCheckController {
	@Autowired
    LastCheckService lastCheckService;
	/**
	 * 结题检查：分页查询项目
	 */
    @GetMapping(value = "/Fproject/list")
    public String getProjectList(Model model,
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "5") Integer size) {
        model.addAttribute("datas", lastCheckService.getApprovedProject(page, size));
        return "last/all_project_list";
}
    /**
     * 结题检查：教职工查询自己的项目
     */
    @GetMapping(value = "/Fprojects")
    public String getMyProjects(Model model,
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "5") Integer size) {
        model.addAttribute("datas", lastCheckService.getUserProject(page, size));
        return "last/my_projects";
    }
    /**
     * 结题检查：对已立项的项目设置实时间期限和项目应该上传的材料说明
     */
    @ResponseBody
    @RequestMapping(value = "/last_set", method = RequestMethod.POST)
    public Map setDeadlineAndMaterialDescription(@RequestParam(value = "code") int projectCode,
                                                 @RequestParam(value = "deadline") String deadline,
                                                 @RequestParam(value = "comment") String description){
        boolean isSave = false;
        try {
            isSave = lastCheckService.updateProject(projectCode, description, deadline);
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
     * 结题检查：上传结题检查材料
     */
    @ResponseBody
    @RequestMapping(value = "/Fupload_material", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadMaterial(@RequestParam("file")MultipartFile file,@RequestParam("projectCode")int projectcode) {
        String pathName = "/Fupload";
        String fileName = file.getOriginalFilename();
        try{
            FileUtil.uploadFile(file.getBytes(),pathName,fileName);
        }catch (Exception e){
            e.printStackTrace();
        }
        lastCheckService.saveLastAddress(projectcode,pathName+fileName);
        return "File is upload successfully";
    }


    @ResponseBody
    @RequestMapping(value = "/Fdownload_material", method = RequestMethod.GET)
    public String downloadMaterial(@RequestParam("projectCode")int projectCode,HttpServletResponse response){
        String filePath = lastCheckService.findLastAddress(projectCode);
        File file = new File(filePath);
        if(file.exists()){
            response.setContentType("application/force-download");
            String fileName = "LastCheckReport";
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
     * 查看已提交材料的项目
     */
    @GetMapping(value = "/findLastCheckProject")
    public String findLastProjectList(Model model,
                                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "limit", defaultValue = "5") Integer size){
        model.addAttribute("projects",lastCheckService.findLastProject(page,size));
        return "last/last_check_project_list";
    }
    /**
     * 结题验收：审核
     * @param
     */
    @ResponseBody
    @RequestMapping(value = "/Fset_status", method = RequestMethod.POST)
    public String setStatus(@RequestBody Map<String,Object> map) {
        lastCheckService.setLastCheckResult(Integer.parseInt((String)map.get("projectcode")),Integer.parseInt((String)map.get("state")));
        return "success!";
    }
}


