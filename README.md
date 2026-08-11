# SimpleOpenAIAgent 🤖

یک **AI Agent ساده** بر پایه OpenAI Responses API که با زبان **Java** نوشته شده است.
این پروژه نشان می‌دهد چگونه می‌توان از قابلیت **Function Calling** (Tool Use) برای ساخت agent های هوشمند استفاده کرد.

---

## 📌 درباره پروژه

این پروژه یک سناریوی واقعی را شبیه‌سازی می‌کند:

> کاربر از هوش مصنوعی می‌پرسد: **«مشخصات MacBook Pro M4 چیه؟»**
> AI به جای پاسخ مستقیم، یک **Tool Call** صادر می‌کند، برنامه اطلاعات محصول را از دیتابیس (in-memory) واکشی کرده و نتیجه را به AI برمی‌گرداند تا پاسخ نهایی را تولید کند.

---

## 🗂 ساختار پروژه

```
src/
└── main/java/com/webrayan/
    ├── Main.java                          ← runner ساده برای تست agent
    └── agent/
        ├── ProductAgent.java              ← منطق اصلی agent و ارتباط با OpenAI
        ├── entity/
        │   ├── Product.java               ← مدل داده محصول (Record)
        │   └── GetProduct.java            ← مدل Tool Schema برای OpenAI
        ├── repository/
        │   └── ProductRepository.java     ← دیتابیس in-memory محصولات
        └── tools/
            └── ProductTool.java           ← لایه اجرای Tool
```

---

## ⚙️ تکنولوژی‌ها

| ابزار | نسخه |
|---|---|
| Java | 17 |
| Maven | - |
| openai-java SDK | 4.43.0 |
| مدل AI | GPT-5 (gpt-5-2) |

---

## 🔄 نحوه کار (Flow)

```
Main.java ──► ProductAgent.ask(question)
    │
    ▼
کاربر (سوال) 
    │
    ▼
OpenAI API ──► Tool Call صادر می‌کند (GetProduct)
    │
    ▼
ProductTool ──► ProductRepository ──► یافتن محصول
    │
    ▼
OpenAI API ──► پاسخ نهایی با اطلاعات محصول
    │
    ▼
خروجی نهایی روی Console
```

---

## 📦 محصولات موجود در دیتابیس

| نام | دسته‌بندی | قیمت (تومان) | موجودی |
|---|---|---|---|
| MacBook Pro M4 | Laptop | ۱۲۰,۰۰۰,۰۰۰ | ۵ |
| Dell XPS 15 | Laptop | ۹۵,۰۰۰,۰۰۰ | ۳ |

---

## 🚀 اجرای پروژه

### پیش‌نیازها
- JDK 17 یا بالاتر
- Maven
- دسترسی به اینترنت (برای OpenAI API)
- تنظیم بودن متغیر محیطی `OPENAI_API_KEY`

### مراحل اجرا

```bash
# ۱. Clone پروژه
git clone <repository-url>
cd SimpleOpenAIAgent

# ۲. Build پروژه
mvn clean install

# ۳. تنظیم API Key
# PowerShell
$env:OPENAI_API_KEY="sk-proj-..."

# ۴. اجرا
mvn exec:java -Dexec.mainClass="com.webrayan.Main"
```

---

## ⚠️ نکات مهم

> API Key در نسخه فعلی از **Environment Variable** خوانده می‌شود که نسبت به hardcode کردن امن‌تر است.
> اگر مقدار `OPENAI_API_KEY` تنظیم نشده باشد، برنامه اجرا نمی‌شود.

```java
// روش فعلی
String apiKey = System.getenv("OPENAI_API_KEY");
```

---

## 🧩 مفاهیم پوشش داده شده

- ✅ OpenAI Responses API
- ✅ Function Calling / Tool Use
- ✅ Tool Schema با Jackson Annotations
- ✅ Multi-turn Conversation (previousResponseId)
- ✅ Java Record برای مدل‌سازی داده
- ✅ Repository Pattern
- ✅ جداسازی runner از منطق agent

---

## 📄 لایسنس

این پروژه صرفاً برای اهداف آموزشی طراحی شده است.
