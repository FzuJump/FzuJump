using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.Networking;
using System.IO;

/* 修改密码 */
public class changeNum : MonoBehaviour
{
    public InputField account;
    public InputField password;

    public class keep
    {
        public int flag;
        public string token;
        public string account;
    }

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void botton()
    {
        this.StartCoroutine(this.Transform_Json_Data());
    }

    public void account_input()
    {
        Debug.Log(account.text);
    }

    public void password_input()
    {
        Debug.Log(password.text);
    }

    IEnumerator Transform_Json_Data()
    {
        //这块代码和之前的不同在于多了文件打开功能
        keep fle = new keep();
        string FileUrl = Application.dataPath + "/JsonData.txt";
        string str = File.ReadAllText(FileUrl);         //打开了之前login.cs脚本里面保存的JsonData.txt文件，把里面的数据内容全部赋值给了fle，如下所示
        fle = JsonUtility.FromJson<keep>(str);          //要访问fle的数据只要fle.flag即可，例子是访问flag查看是否登录成功，fle里面有什么数据参考keep类
        Debug.Log(fle.token);
        ///后面这些代码不用管
        WWWForm form = new WWWForm();
        form.AddField("password", password.text);
        form.AddField("usercode", account.text);


        UnityWebRequest req = UnityWebRequest.Post("http://49.234.32.149:8082/appUpdate", form);
        req.SetRequestHeader("token", fle.token);
        //登录成功的时候保存token
        yield return req.SendWebRequest();
        string info = req.downloadHandler.text;
        Debug.Log(info);
    }
}
