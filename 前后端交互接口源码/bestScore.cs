using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.Networking;
using System.IO;

/* 获取个人最好成绩接口*/
public class bestScore : MonoBehaviour
{
    public InputField account;  

    public class information
    {
        public string msg;
        public int ItemNumber;
        public int JumpFrequency;
        public int Code;
    }

    public class keep
    {
        public int ItemNumber;
        public int JumpFrequency;
        public string account;
    }

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

    IEnumerator Transform_Json_Data()
    {
        WWWForm form = new WWWForm();
        form.AddField("usercode", account.text);

        UnityWebRequest req = UnityWebRequest.Post("http://49.234.32.149:8082/appSearch", form);
        yield return req.SendWebRequest();
        string bestdata = req.downloadHandler.text;
        Debug.Log(bestdata);                            
        information res = new information();
        res = JsonUtility.FromJson<information>(bestdata);          //可以在这里面直接调用想要的数据，例如道具数res.ItemNumber、跳跃高度res.JumpFrequency，但是不建议这样做，推荐使用去文件中读取的形式，参考下面的JsonData3文件
        Debug.Log(res.ItemNumber);
        Debug.Log(res.JumpFrequency);

        keep fle = new keep();
        fle.ItemNumber = res.ItemNumber;
        fle.JumpFrequency = res.JumpFrequency;
        fle.account = account.text;
        string str = JsonUtility.ToJson(fle);
        string FileUrl = Application.dataPath + "/JsonData3.txt"; //JsonData3文件放道具数fle.ItemNumber、跳跃高度fle.JumpFrequency、当前查询的账号fle.account，需要什么数据可以打开这个文件然后调用这些数据
        File.WriteAllText(FileUrl, str);
        Debug.Log(fle.ItemNumber);
        Debug.Log(fle.JumpFrequency);

    }
}
