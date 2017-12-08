/**
 * Created by DJ on 2017/12/6.
 */
function toText(html) {
    // var text = html.replace(/<br\/>/g,"\r\n");
    var text = html.replace(/<br\s*\/?>/gi,"\r\n");
    text = text.replace(/&quot/g,"\"");
    return text;
}
