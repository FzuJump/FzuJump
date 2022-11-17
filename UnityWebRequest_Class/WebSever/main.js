var express = require("express");
var path = require("path");
var app = express();
app.listen(6080);
app.use("/", express.static(path.join(process.cwd(), "www_root")));

app.get("/uploadData", function(req, res)
{
    console.log(req.query);
    res.send("BYCWUnityWebRequest!");
})
