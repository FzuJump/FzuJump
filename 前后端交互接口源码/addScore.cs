using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.Networking;
using System.IO;
/* 添加成绩模块 */
public class addScore : MonoBehaviour
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
        keep fle = new keep();
        string FileUrl = Application.dataPath + "/JsonData.txt";
        string str = File.ReadAllText(FileUrl);
        fle = JsonUtility.FromJson<keep>(str);
        Debug.Log(fle.token);                                       //打开文件

        WWWForm form = new WWWForm();
        form.AddField("itemNumber", password.text);
        form.AddField("jumpFrequency", account.text);
        form.AddField("rolename","帅哥");
        form.AddField("userCode",fle.account);
        form.AddField("userName","匿名用户");                       //这里注意一下，要把52和53行代码后面的password.text和account.text改成你对应的拾取道具数和跳跃高度的变量



        UnityWebRequest req = UnityWebRequest.Post("http://49.234.32.149:8082/appAdd",form);
        req.SetRequestHeader("token", fle.token);
        //登录成功的时候保存token
        yield return req.SendWebRequest();
        string info = req.downloadHandler.text;
        Debug.Log(info);
    }
}
