using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.Networking;
using System.IO;

/* 输出排行榜信息 */
public class getbank : MonoBehaviour
{
    public class keep
    {
        public int flag;
        public string token;
        public string account;
    }

    public class keep_data
    {
         public string n0;
         public string n1;
         public string n2;
         public string n3;
         public string n4;
         public string n5;
         public string n6;
         public string n7;
         public string n8;
         public string n9;
         public string n10;
         public string n11;
         public string n12;
         public string n13;
         public string n14;
         public string n15;
         public string n16;
         public string n17;
         public string n18;
         public string n19;
         public string n20;
         public string n21;
         public string n22;
         public string n23;
         public string n24;
         public string n25;
         public string n26;
         public string n27;
         public string n28;
         public string n29;
        public string Code;
    }                       //这里定义了排行榜的所有数据所保存的类，总共有n0-n29 30个数据，最后那个code不用管，这里面的数据要几个取几个就行
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

    IEnumerator Transform_Json_Data()
    {
        keep fle = new keep();
        string FileUrl = Application.dataPath + "/JsonData.txt";
        string str = File.ReadAllText(FileUrl);
        fle = JsonUtility.FromJson<keep>(str);
        Debug.Log(fle.token);                                   //之前已经介绍过了，打开JsonData.txt文件，后面打开文件都可以用这个模板，主要是变量名和文件名改一下就行，还有很重要一点就是如果打开的是排行榜文件，注意把里面的keep类改为keep_data类

        WWWForm form = new WWWForm();
        form.AddField("password", "1");


        UnityWebRequest req = UnityWebRequest.Post("http://49.234.32.149:8082/appRank", form);
        req.SetRequestHeader("token", fle.token);
        //登录成功的时候保存token
        yield return req.SendWebRequest();
        string info = req.downloadHandler.text;
        Debug.Log(info);

        //JsonData1存放排行榜的信息
        keep_data res = new keep_data();
        res = JsonUtility.FromJson<keep_data>(info);        
        Debug.Log(info);                                        

        string str1 = JsonUtility.ToJson(res);
        string FileUrl1 = Application.dataPath + "/JsonData1.txt";
        File.WriteAllText(FileUrl1, str1);                      //这边要注意一下，我把后端返回过来的数据都保存在了JsonData1.txt这个文件了，要用的时候打开就行，注意看我里面用的是keep_data这个类，所以打开文件时要注意修改类名
    }
}
