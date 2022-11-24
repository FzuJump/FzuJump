using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.Networking;
using System.IO;
/* 注册功能 */
//这块代码不用看，直接可以在botton函数里面加页面跳转功能就行
public class Register : MonoBehaviour
{
    public InputField account;
    public InputField password;
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
        WWWForm form = new WWWForm();
        form.AddField("password", password.text);
        form.AddField("usercode", account.text);

        UnityWebRequest req = UnityWebRequest.Post("http://49.234.32.149:8082/register", form);
        yield return req.SendWebRequest();
        string info = req.downloadHandler.text;
        Debug.Log(info);
    }
}
