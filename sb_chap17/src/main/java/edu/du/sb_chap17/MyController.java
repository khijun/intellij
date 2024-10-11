package edu.du.sb_chap17;

import edu.du.sb_chap17.model.Article;
import edu.du.sb_chap17.model.ArticleListModel;
import edu.du.sb_chap17.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyController {
    @Autowired
    ListArticleService listSerivce;

    @Autowired
    ReadArticleService readSerivce;
    @Autowired
    WriteArticleService writeSerivce;
    @Autowired
    DeleteArticleService deleteSerivce;

    @GetMapping("/list")
    public String list(Model model, HttpServletRequest request) {
        String pageNumberString = request.getParameter("p");
        int pageNumber = 1;
        if (pageNumberString != null && pageNumberString.length() > 0) {
            pageNumber = Integer.parseInt(pageNumberString);
        }

        ArticleListModel articleListModel = listSerivce.getArticleList(pageNumber);
        model.addAttribute("listModel", articleListModel);

        if (articleListModel.getTotalPageCount() > 0) {
            int beginPageNumber =
                    (articleListModel.getRequestPage() - 1) / 10 * 10 + 1;
            int endPageNumber = beginPageNumber + 9;
            if (endPageNumber > articleListModel.getTotalPageCount()) {
                endPageNumber = articleListModel.getTotalPageCount();
            }
            model.addAttribute("beginPage", beginPageNumber);
            model.addAttribute("endPage", endPageNumber);
        }
        return "list_view";
    }

    @GetMapping("/article_not_found")
    public String articleNotFound() {
        return "article_not_found";
    }

    @GetMapping("/read")
    public String read(Model model, HttpServletRequest request) {
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        try {
            Article article = readSerivce.readArticle(articleId);
            model.addAttribute("article", article);
            return "read_view";
        } catch (ArticleNotFoundException ex) {
            return "article_not_found";
        }
    }

    @GetMapping("writeForm")
    public String writeForm(Model model, HttpServletRequest request) {
        return "writeForm";
    }

    @PostMapping("write")
    public String write(Model model, HttpServletRequest request) {
        WritingRequest writingRequest = new WritingRequest(request.getParameter("writerName"), request.getParameter("password"),
                request.getParameter("title"), request.getParameter("content"));
        try {
            Article postedArticle =
                    writeSerivce.write(writingRequest);
            model.addAttribute("postedArticle", postedArticle);
        } catch (IdGenerationFailedException e) {

        }
        return "write";
    }

    @GetMapping("delete_form")
    public String deleteForm() {
        return "delete_form";
    }

    @PostMapping("delete")
    public String delete(HttpServletRequest request) {
        try {
            DeleteRequest deleteRequest = new DeleteRequest(Integer.parseInt(request.getParameter("articleId")),
                    request.getParameter("password"));
            deleteSerivce.deleteArticle(deleteRequest);
            return "delete_success";
        } catch (Exception ex) {
            request.setAttribute("deleteException", ex);
            return "delete_error";
        }
    }

}
