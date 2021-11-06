<%--
  Created by IntelliJ IDEA.
  User: carol
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Create Forum -->
<c:choose>
    <c:when test="${user != null}">
        <button type="button" class="btn btn-light" data-toggle="modal" data-target="#createModal">
            Create new forum
        </button>
    </c:when>
    <c:otherwise>
        <p>Log in or sign up to create forums and add comments</p>
    </c:otherwise>
</c:choose>

<div class="modal fade" id="createModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">New forum</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form name="forum" action="ServletController" method="post" enctype='multipart/form-data'>
                <input type="hidden" name="cmd" value="forum">
                <input type="hidden" name="forumcmd" value="create">
                <input type="hidden" name="u_name" value="${user.getUsername()}">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="title" class="col-form-label">Title:</label>
                        <input type="text" name="f_title" id="title" required>
                    </div>
                    <div class="form-group">
                        <label for="image" class="col-form-label">Image (optional):</label>
                        <input type="file" name="f_image" id="image" multiple accept=".jpg, .png"/><br>
                    </div>
                    <div class="form-group">
                        <label for="message-text" class="col-form-label">Content:</label>
                        <textarea class="form-control" name="f_content" id="message-text" required></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <input type="submit" value="Create forum">
                </div>
            </form>
        </div>
    </div>
</div>
