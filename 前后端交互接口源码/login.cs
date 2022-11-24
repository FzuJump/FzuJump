using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.Networking;
using System.IO;

/* 用户登录功能 */
public class login : MonoBehaviour
{
    public InputField account;  //存放文本框输入的字符串，注意在挂载的时候要挂载到相应的文本框，比如这个就挂载到账号输入
    public InputField password; //同上
    int flag;                   //标记是否登录成功



    

    public class information
    {
        public int code;
        public string msg;
        public string data;
    }                           //这个是用来解析后端传过来的数据所定义的类，无需了解

    public class keep
    {
        public int flag;
        public string token;
        public string account;
    }                           //这个是用来保存登录后数据文件所定义的类，flag标记是否成功登录，token不用管已经封装好了，account保存你现在输入的账号



    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {

    }

    public void botton()
    {
        this.StartCoroutine(this.Transform_Json_Data());    //booton函数，用来挂载到按钮上，真正实现交互功能的是里面那个this
    }
    
    public void account_input()
    {
        Debug.Log(account.text);                        //账号输入函数，用来挂载到对应的文本输入框，这个函数可以不用，这里只是做一个接口，如果前端没写这个可以直接删掉
    }

     public void password_input()
    {
        Debug.Log(password.text);           
    }

    IEnumerator Transform_Json_Data()
    {
        flag = 0;
        WWWForm form = new WWWForm();
        form.AddField("username", account.text);
        form.AddField("password", password.text);   //这两行用于将文本输入框里面的内容填入到网络表格中，注意这里的命名，如果第11、12行变量的名称改动了，这里.text前的变量名称也要相应改变

        UnityWebRequest req = UnityWebRequest.Post("http://49.234.32.149:8082/tologin", form);
        //登录成功的时候保存token
        yield return req.SendWebRequest();
        string info = req.downloadHandler.text;
        Debug.Log(info);                            //这里出现输出函数的都是调试用的，最后可以删去

        information res = new information();
        res = JsonUtility.FromJson<information>(info);
        Debug.Log(info);                            //这块代码是将返回的文本信息转化为json数据格式，方便后面的调用，可以不用管

        if (res.code==200)
        {
            flag = 1;
        }
        else
        {
            flag = 0;
        }


        //保存token和flag还有account到JsonData文件中
        keep fle = new keep();
        fle.flag = flag;
        fle.token = res.msg;
        fle.account = account.text;
        string str = JsonUtility.ToJson(fle);
        string FileUrl = Application.dataPath + "/JsonData.txt"; //这个代码是将返回文本里面有用的数据放到JsonData.txt文件里面，这里的文件名可以更改，不过注意其他脚本里面有用到JsonData.txt文件的也需要更改
        File.WriteAllText(FileUrl, str);//这个可以去asserts里面的JsonData.txt文件查看写进去的内容，主要包括flag、token、account

    }
}
