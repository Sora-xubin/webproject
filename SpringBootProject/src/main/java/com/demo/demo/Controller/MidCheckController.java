package com.demo.demo.Controller;

import com.demo.demo.Entity.Project;
import com.demo.demo.Service.MidCheckService;
import javassist.bytecode.analysis.MultiType;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MidCheckController {
    @Autowired
    MidCheckService midCheckService;

    /**
     * 中期检查：分页查询已立项的项目
     * @return
     */
    @GetMapping(value = "/project/list")
    public String getProjectList(Model model,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "5") Integer size) {
        model.addAttribute("datas", midCheckService.getApprovedProject(page, size));
        return "middle/all_project_list";
    }

    /**
     * 中期检查：教职工查询自己的项目
     * @param model
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/projects")
    public String getMyProjects(Model model,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "5") Integer size) {
        model.addAttribute("datas", midCheckService.getUserProject(page, size));
        return "middle/my_projects";
    }

    /**
     * 中期检查：对已立项的项目设置实时间期限和项目应该上传的材料说明
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
     * 中期检查：上传中期检查材料
     * @return String
     */
    @ResponseBody
    @RequestMapping(value = "/upload_material", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadMaterial(@RequestParam("file")MultipartFile file) throws IOException {
        File convertFile = new File("/upload" + file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        return "File is upload successfully";
    }

    /**
     * 中期检查：下载中期检查材料
     */
    @ResponseBody
    @RequestMapping(value = "/download_material", method = RequestMethod.GET)
    public ResponseEntity<Object> downloadMaterial(int projectCode) throws IOException {
        Project project = midCheckService.findProjectByCode(projectCode);
        String filename = project.getMidreport();
        File file = new File(filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", filename);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
        return responseEntity;
    }

    /**
     * 中期检查：审核
     * @param id
     */
    @ResponseBody
    @RequestMapping(value = "/set_status", method = RequestMethod.POST)
    public String setStatus(int id) {
        boolean isPassed = true;
        if (isPassed) {
            return "S";
        } else {
            return "F";
        }
    }
}
