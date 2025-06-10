package org.scoula.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice// 모든 Controller에 적용되는 전역 예외 처리기
@Log4j2
public class CommonExceptionAdvice {

    // 모든 예외를 포괄적으로 처리
    @ExceptionHandler(Exception.class)
    public String except(Exception ex, Model model) {
        log.error("Exception 발생: " + ex.getMessage());
        log.error("Stack Trace: ", ex);// 상세 에러 로그

        // 예외 정보를 뷰로 전달
        model.addAttribute("exception", ex);
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("timestamp", System.currentTimeMillis());

        return "error_page";// 에러 페이지로 이동
    }

    // 특정 예외 타입별 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgument(IllegalArgumentException ex, Model model) {
        log.warn("잘못된 인자: " + ex.getMessage());

        model.addAttribute("errorType", "INVALID_ARGUMENT");
        model.addAttribute("exception", ex);

        return "error_page";
    }

    // 숫자 형변환 예외 처리
    @ExceptionHandler(NumberFormatException.class)
    public String handleNumberFormat(NumberFormatException ex, Model model) {
        log.warn("숫자 변환 오류: " + ex.getMessage());

        model.addAttribute("errorType", "NUMBER_FORMAT_ERROR");
        model.addAttribute("errorMessage", "숫자 형식이 올바르지 않습니다.");
        model.addAttribute("exception", ex);

        return "error_page";
    }
}