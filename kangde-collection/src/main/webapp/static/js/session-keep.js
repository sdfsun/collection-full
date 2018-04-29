var eu = eu || {};

eu.contextPath = ctx==undefined ?"":ctx;
/**
 * 保持心跳
 */
window.setInterval(sessionInfo, 30 * 60 * 1000);
function sessionInfo() {
    $.ajax({
        type: "GET",
        url: eu.contextPath+"/login/sessionInfo",
        cache: false,
        dataType: "json",
        success: function (data) {

        }});
}