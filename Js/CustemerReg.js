let home=document.getElementById("home")
home.addEventListener("click",()=>{
    window.location.href="index.html";
})


let subbtn = document.getElementById("reg-input-btn");
subbtn.addEventListener("click", async (e) => {
  e.preventDefault();
  let isPinValid = await districtApi();
  if (nameValid()) {
    let span = document.getElementById("name-span");
    span.style.display = "block";
  } else if (!emailValid()) {
    let span = document.getElementById("email-span");
    span.innerHTML = "Enter a valid email";
    span.style.display = "block";
  } else if (phoneValid()) {
    let span = document.getElementById("phone-span");
    span.style.display = "block";
  } else if (fcValid()) {
    let span = document.getElementById("family-span");
    span.style.display = "block";
  } else if (pswValidation()) {
  } else if (confirmPsw()) {
    let span = document.getElementById("cpsw-valid");
    span.style.display = "block";
    span.innerHTML = "Password not matching";
    span.style.color = "red";
  } else if (dnoValid()) {
    let span = document.getElementById("dno-span");
    span.style.display = "block";
  } else if (streetValid()) {
    let span = document.getElementById("street-span");
    span.style.display = "block";
  } else if (landmarkValid()) {
    let span = document.getElementById("landmark-span");
    span.style.display = "block";
  } else if (!isPinValid) {
  } else {
    console.log("All done");
    subbtn.value = "";
    let load = document.getElementById("load");
    load.style.display = "block";
    sendCustomerData();
  }
});

// --- VALIDATION ---
//pincode API

async function districtApi() {
  let inPin = document.getElementById("in-pincode").value;

  if (inPin.length === 6) {
    let span = document.getElementById("pin-valid");

    try {
      let apidata = await window.fetch(
        "https://api.postalpincode.in/pincode/" + inPin
      );
      let apijson = await apidata.json();
      let status = apijson[0].Status;

      if (status === "Success") {
        let districtTag = document.getElementById("in-district");
        let stateTag = document.getElementById("in-state");
        var district = apijson[0].PostOffice[0].District;
        var state = apijson[0].PostOffice[0].State;

        span.style.display = "none";
        districtTag.setAttribute("value", district);
        stateTag.setAttribute("value", state);

        return { district: district, state: state }; // Pin code is valid
      } else {
        span.style.display = "block";
        span.innerHTML = "Wrong PIN Code";
        span.style.color = "red";
        return false; // Pin code is invalid
      }
    } catch (error) {
      console.error("Error fetching pin code data:", error);
      return false; // Handle API error
    }
  } else {
    let span = document.getElementById("pin-valid");
    span.style.display = "block";
    span.innerHTML = "Invalid PIN Code";
    span.style.color = "red";
    return false; // Invalid pin code format
  }
}

// Name validation
function nameValid() {
  let demo = document.getElementById("in-name");
  if (demo.value === "") {
    return true;
  } else {
    let span = document.getElementById("name-span");
    span.style.display = "none";
    return false;
  }
}
// Email validation
function emailValid() {
  let email = document.getElementById("in-email").value;
  if (
    email !== email.slice(0, -10) + "@gmail.com" ||
    email.slice(0, -10) === ""
  ) {
    return false;
  } else {
    let span = document.getElementById("email-span");
    span.style.display = "none";
    return true;
  }
}
// Phone validation
function phoneValid() {
  let demo = document.getElementById("in-phone");
  if (demo.value === "") {
    return true;
  } else if (demo.value.length !== 10) {
    let span = document.getElementById("phone-span");
    span.innerHTML = "Enter Valid Phone no";
    span.style.display = "none";
    return true;
  } else {
    let span = document.getElementById("phone-span");
    span.style.display = "none";
    return false;
  }
}
// Family count
function fcValid() {
  let demo = document.getElementById("in-famcount");
  if (demo.value === "") {
    return true;
  } else {
    let span = document.getElementById("family-span");
    span.style.display = "none";
    return false;
  }
}
// Door No
function dnoValid() {
  let demo = document.getElementById("in-dno");
  if (demo.value === "") {
    return true;
  } else {
    let span = document.getElementById("dno-span");
    span.style.display = "none";
    return false;
  }
}
// Street
function streetValid() {
  let demo = document.getElementById("in-street");
  if (demo.value === "") {
    return true;
  } else {
    let span = document.getElementById("street-span");
    span.style.display = "none";
    return false;
  }
}
// Land Mark
function landmarkValid() {
  let demo = document.getElementById("in-landmark");
  if (demo.value === "") {
    return true;
  } else {
    let span = document.getElementById("landmark-span");
    span.style.display = "none";
    return false;
  }
}

//        PASSWORD VALIDATION

// Password and Confird Password same validation

function confirmPsw() {
  let psw = document.getElementById("in-psw").value;
  let cpsw = document.getElementById("in-cpsw").value;
  if (psw === cpsw) {
    let span = document.getElementById("cpsw-valid");
    span.style.display = "none";
    return false;
  } else {
    return true;
  }
}

// Password validation ---- Regular expression   regularExpression = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,12}$/;

function pswValidation() {
  var list = document.getElementById("psw-valid");
  var psw = document.getElementById("in-psw").value;
  list.style.display = "block";
  if (psw.length > 8) {
    let li = document.getElementById("pvl1");
    li.style.color = "green";
  } else {
    let li = document.getElementById("pvl1");
    li.style.color = "red";
  }
  if (psw.search(/[A-Z]/) >= 0) {
    let li = document.getElementById("pvl2");
    li.style.color = "green";
  } else {
    let li = document.getElementById("pvl2");
    li.style.color = "red";
  }
  if (psw.search(/[a-z]/) >= 0) {
    let li = document.getElementById("pvl3");
    li.style.color = "green";
  } else {
    let li = document.getElementById("pvl3");
    li.style.color = "red";
  }
  if (psw.search(/[!@#$%^&*]/) >= 0) {
    let li = document.getElementById("pvl4");
    li.style.color = "green";
  } else {
    let li = document.getElementById("pvl4");
    li.style.color = "red";
  }
  if (psw.search(/[0-9]/) >= 0) {
    let li = document.getElementById("pvl5");
    li.style.color = "green";
  } else {
    let li = document.getElementById("pvl5");
    li.style.color = "red";
  }
  let regx = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}$/;
  if (regx.test(psw) === false) {
    return true;
  } else {
    list.style.display = "none";
    return false;
  }
}

// Password show or hide
function showpsw() {
  let psw = document.getElementById("in-psw");
  let eye = document.getElementById("eye");
  show(psw, eye);
}
function hidepsw() {
  let psw = document.getElementById("in-psw");
  let eye = document.getElementById("eye");
  hide(psw, eye);
}
function showcpsw() {
  let psw = document.getElementById("in-cpsw");
  let eye = document.getElementById("ceye");
  show(psw, eye);
}
function hidecpsw() {
  let psw = document.getElementById("in-cpsw");
  let eye = document.getElementById("ceye");
  hide(psw, eye);
}
function showlpsw() {
  let psw = document.getElementById("log-in-psw");
  let eye = document.getElementById("log-eye");
  show(psw, eye);
}
function hidelpsw() {
  let psw = document.getElementById("log-in-psw");
  let eye = document.getElementById("log-eye");
  hide(psw, eye);
}
function show(psw, eye) {
  psw.setAttribute("type", "text");
  eye.setAttribute("class", "fa-solid fa-eye-slash");
}
function hide(psw, eye) {
  psw.setAttribute("type", "password");
  eye.setAttribute("class", "fa-solid fa-eye");
}

// SIGN IN  and   SIGN UP link

let signin = document.getElementById("signin-btn");
signin.addEventListener("click", () => {
  document.getElementById("formdiv").style.opacity = "0";
  setTimeout(() => {
    document.getElementById("formdiv").style.display = "none";
    document.getElementById("signindiv").style.display = "flex";
    setTimeout(() => {
      document.getElementById("signindiv").style.opacity = "1";
    }, 100);
  }, 110);
});

let signup = document.getElementById("signup-btn");
signup.addEventListener("click", () => {
  document.getElementById("signindiv").style.opacity = "0";
  setTimeout(() => {
    document.getElementById("signindiv").style.display = "none";
    document.getElementById("formdiv").style.display = "flex";
    setTimeout(() => {
      document.getElementById("formdiv").style.opacity = "1";
    }, 100);
  }, 110);
});

// --- INTEGRATION for create account  ---
async function sendCustomerData() {
  let fullname = document.getElementById("in-name").value;
  let email = document.getElementById("in-email").value;
  let phone = document.getElementById("in-phone").value;
  let fc = document.getElementById("in-famcount").value;
  let pwd = document.getElementById("in-psw").value;
  let dno = document.getElementById("in-dno").value;
  let street = document.getElementById("in-street").value;
  let landmark = document.getElementById("in-landmark").value;
  let pincode = document.getElementById("in-pincode").value;
  let distInfo = await districtApi();
  let district = distInfo.district;
  let state = distInfo.state;

  const customerobj = {
    name: fullname,
    email: email,
    phone: phone,
    pwd: pwd,
    familyCount: fc,
    address: {
      d_no: dno,
      street: street,
      landmark: landmark,
      pincode: pincode,
      district: district,
      state: state,
    },
  };
  const customer = JSON.stringify(customerobj);
  console.log(customer);

  try {
    const response = await fetch("http://localhost:8080/customer", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: customer,
    });
    if (response.status === 201) {
      subbtn.value = "";
      let load = document.getElementById("load");
      load.style.display = "block";

      const data = await response.json();
      console.log(data);
      console.log("Successfull");

      document.getElementById("formdiv").style.opacity = "0";
      setTimeout(() => {
        document.getElementById("formdiv").style.display = "none";
        document.getElementById("signindiv").style.display = "flex";
        setTimeout(() => {
          document.getElementById("signindiv").style.opacity = "1";
        }, 100);
      }, 110);
      document.getElementById("clarity1").style.display = "block";
    }
    if (response.status === 400) {
      let span = document.getElementById("email-span");
      span.style.display = "block";
      span.innerHTML = "Already have a account with this Email";
      console.log("customer already Registered");
    }
  } catch (error) {
    console.error("error", error);
  }
}

// ------ LOGIN ------
let logbtn = document.getElementById("log-input-btn");
logbtn.addEventListener("click", async (e) => {
  e.preventDefault();
  let email = document.getElementById("log-in-email").value;
  let psw = document.getElementById("log-in-psw").value;

  let loginApi = await window.fetch(
    `http://localhost:8080/customer?email=${email}&pwd=${psw}`
  );
  let loginJson = await loginApi.json();
  console.log(loginJson);

  if (loginJson.data === "wrong email") {
    let span = document.getElementById("wemail");
    span.style.display = "block";
  } else if (loginJson.data === "wrong psw") {
    let span = document.getElementById("wpsw");
    span.style.display = "block";
  } else {
    let id = loginJson.data.id;
    let name = loginJson.data.name;
    let email = loginJson.data.email;
    let phone = loginJson.data.phone;
    let fc = loginJson.data.familyCount;
    let aid = loginJson.data.address.id;
    let dno = loginJson.data.address.d_no;
    let street = loginJson.data.address.street;
    let landmark = loginJson.data.address.landmark;
    let pincode = loginJson.data.address.pincode;
    let district = loginJson.data.address.district;
    let state = loginJson.data.address.state;

    window.localStorage.setItem("id", id);
    window.localStorage.setItem("name", name);
    window.localStorage.setItem("email", email);
    window.localStorage.setItem("phone", phone);
    window.localStorage.setItem("familycount", fc);
    window.localStorage.setItem("dno",dno);
    window.localStorage.setItem("street",street);
    window.localStorage.setItem("landmark",landmark);
    window.localStorage.setItem("pincode",pincode);
    window.localStorage.setItem("district",district);
    window.localStorage.setItem("state",state);
    window.localStorage.setItem("aid",aid);


    window.location.replace("http://127.0.0.1:5500/CustomerHome.html");
    console.log("done");
  }
});

