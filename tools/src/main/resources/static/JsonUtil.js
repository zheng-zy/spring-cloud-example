escapezip: function (ctype) {
    var txtA = document.getElementById("jsonval");
    var text = txtA.value;
    if (!text.trim()) {
        alert("请输入JSON字符串。");
        return false;
    }
    if (ctype == 1 || ctype == 3) {
        text = text.split("\n").join(" ");
        var t = [];
        var inString = false;
        for (var i = 0, len = text.length; i < len; i++) {
            var c = text.charAt(i);
            if (inString && c === inString) {
                if (text.charAt(i - 1) !== '\\') {
                    inString = false;
                }
            } else if (!inString && (c === '"' || c === "'")) {
                inString = c;
            } else if (!inString && (c === ' ' || c === "\t")) {
                c = '';
            }
            t.push(c);
        }
        text = t.join('');
    }
    if (ctype == 2 || ctype == 3) {
        text = text.replace(/\\/g, "\\\\").replace(/\"/g, "\\\"");
    }
    if (ctype == 4) {
        text = text.replace(/\\\\/g, "\\").replace(/\\\"/g, '\"');
    }
    txtA.value = text;
}