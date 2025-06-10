package org.scoula.ex03.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.ex03.dto.SampleDTO;
import org.scoula.ex03.dto.SampleDTOList;
import org.scoula.ex03.dto.TodoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.HttpHeaders;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SampleController {
    @RequestMapping(value="",method={RequestMethod.GET,RequestMethod.POST})
    public String basic(){
        log.info("[GET]/sample/ 요청 처리됨 ");
        return "sample/input_Page";
    }

    @RequestMapping(value="/basic2",method={RequestMethod.GET})
    public void basic2(){
        log.info("[GET]/sample/basic2 요청 처리됨 ");
    }

    @RequestMapping("/board/{id}")
    public void selectBoard(@PathVariable("id") long id){
        log.info("입력된 id : "+id);
    }

    @GetMapping("/ex01")
    public String ex01(SampleDTO dto) {  // HandlerAdapter가 자동으로 객체 생성 및 프로퍼티 바인딩
        log.info("" + dto);   // 바인딩된 데이터 로그 출력으로 확인
        return "sample/ex01"; // ViewResolver에 의해 /WEB-INF/views/sample/ex01.jsp로 forward
    }

    @GetMapping("/ex02")
    public String ex02(@RequestParam("name")String name,@RequestParam("age")int age) {
        log.info("name : "+name+", age : "+age);
        return "sample/ex02";
    }

    @GetMapping("/ex02-advanced")
    public String ex02Advanced(
            @RequestParam(value="name", required=false, defaultValue="익명") String name,
            @RequestParam(value="age", required=false, defaultValue="10") int age) {
        log.info("name : "+name+", age : "+age);
        return "sample/ex02";
    }

    @GetMapping("/ex02List")
    public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
        log.info("ids: " + ids);
        return "ex02List";
        // 동일한 파라미터명으로 전송된 여러 값을 List로 자동 수집
    }

    @GetMapping("/ex02Bean")
    public String ex02Bean(SampleDTOList list) {
        log.info("list dtos: " + list);
        return "ex02Bean";
        // 복합 객체 내부의 리스트도 자동 바인딩 처리
    }

    @GetMapping("/ex03")
    public String ex03(TodoDTO todo) {
        log.info("todo: " + todo);
        return "ex03";
        // @DateTimeFormat에 지정된 패턴에 맞는 문자열만 Date로 변환
    }

    @GetMapping("/ex04")
    public String ex04(SampleDTO dto, int page) {
        log.info("dto: " + dto);
        log.info("page: " + page);
        return "sample/ex04";
    }
    @GetMapping("/ex05")
    public void ex05() {
        log.info("/ex05........");
        // 리턴값이 없으면 RequestMapping의 경로를 뷰 이름으로 사용
    }
    @GetMapping("/ex06")
    public String ex06(RedirectAttributes ra) {
        log.info("/ex06........");
        // 리다이렉트 시에도 데이터 전달 가능 (일회성)
        ra.addAttribute("name", "AAA");  // URL 파라미터로 전달
        ra.addAttribute("age", 10);
        return "redirect:/sample/ex06-2";  // 클라이언트에게 새로운 URL로 재요청 지시
    }
    @GetMapping("/ex07")
    public @ResponseBody SampleDTO ex07() {  // @ResponseBody: HTTP 응답 바디에 직접 데이터 출력
        log.info("/ex07........");
        SampleDTO dto = new SampleDTO();
        dto.setAge(10);
        dto.setName("홍길동");
        return dto;  // Jackson이 DTO 객체를 JSON 문자열로 자동 변환
    }

    @GetMapping("/ex08")
    public ResponseEntity<String> ex08() {
        log.info("/ex08..........");
        String msg = "{\"name\": \"홍길동\"}";  // JSON 문자열 직접 생성

        // HTTP 헤더 직접 설정
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json;charset=UTF-8");

        // HTTP 상태 코드, 헤더, 바디를 모두 직접 제어
        return new ResponseEntity<>(msg, header, HttpStatus.OK);
    }

    @GetMapping("/exUpload")
    public String exUpload() {
        log.info("/exUpload..........");
        return "sample/exUpload"; // forward
    }

    @PostMapping("/exUploadPost")
    public void exUploadPost(ArrayList<MultipartFile> files) {
        // MultipartFile: Spring이 제공하는 업로드 파일 래퍼 클래스
        for(MultipartFile file : files) {
            log.info("----------------------------------");
            log.info("name:" + file.getOriginalFilename());  // 원본 파일명
            log.info("size:" + file.getSize());              // 파일 크기 (바이트)
            log.info("contentType:" + file.getContentType()); // MIME 타입

            // 파일이 실제로 선택되었는지 확인
            if (!file.isEmpty()) {
                try {
                    // 파일을 지정된 위치에 저장
                    File saveFile = new File("c:/upload/" + file.getOriginalFilename());
                    file.transferTo(saveFile);  // 임시 파일을 최종 위치로 이동
                } catch (IOException e) {
                    log.error("파일 저장 실패", e);
                }
            }
        }
    }
}
