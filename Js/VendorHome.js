let vid = window.localStorage.getItem('id')
let vname = window.localStorage.getItem('name')
let email = window.localStorage.getItem('email')
let phone = window.localStorage.getItem('phone')
let fc = window.localStorage.getItem('familycount')

let userid = document.getElementById('userid').innerHTML = vid
let username = document.getElementById('username').innerHTML = vname
let useremail = document.getElementById('useremail').innerHTML = email
let userphone = document.getElementById('userphone').innerHTML = phone

//----- Log out---------------------------------------------------------------------------

let logout = document.getElementById('logout')
logout.addEventListener("click", () => {
    window.localStorage.clear()
    window.location.replace('http://127.0.0.1:5500/VendorRegistration.html')
})

//delete -----------------------------------------------------------------------------------

let deleteuser = document.getElementById("userdelete")
deleteuser.addEventListener('click', () => {
    document.getElementById('pop').style.display = 'grid'
})
let canceldelete = document.getElementById('cancel-delete')
canceldelete.addEventListener('click', () => {
    document.getElementById('pop').style.display = 'none'
})
let dodelete = document.getElementById('do-delete')
dodelete.addEventListener("click", async () => {
    console.log('user deleted');
    await window.fetch(`http://localhost:8080/vendor?id=${userid}`, {
        method: "DELETE"
    })
    window.localStorage.clear()
    window.location.replace('http://127.0.0.1:5500/VendorRegistration.html')
})

// works list -----------------------------------------------------------------------------
async function worksList() {
    let awb = document.getElementById('awb')
    let owb = document.getElementById('owb')
    let cwb = document.getElementById('cwb')
    document.getElementById('works-list-div1').style.display = 'flex'
    document.getElementById('works-list-div2').style.display = 'none'
    document.getElementById('works-list-div3').style.display = 'none'
    awb.style.borderBottom = '2px solid black'
    owb.style.borderBottom = 'none'
    cwb.style.borderBottom = 'none'

    let wl = await fetch(`http://localhost:8080/getallworks?v_id=${localStorage.getItem('id')}`)
    let wljson = await wl.json()
    // console.log(wljson);

    let workdiv = document.getElementById('works-list-div1')
    workdiv.innerHTML = ''

    if (wljson.data.length === 0) {
        let workdiv = document.getElementById('works-list-div1')
        workdiv.innerHTML = `<h2>No works found</h2>`
    } else {
        for (const ele of wljson.data) {
            // console.log(ele);
            // console.log(ele.type);
            let workdiv = document.getElementById('works-list-div1')
            workdiv.innerHTML += `<div id="work-item-div">
    <div id="work-item-div2">
        <h2 id="g-work-name">${ele.type}</h2>
        <p id="g-work-desc">${ele.description}</p>
        <span id="g-work-id">work id : <span id="g-work-id2">${ele.id}</span></span>
    </div>
    <div id="item-address">
        <div id="id1"><span id="g-work-hno">${ele.address.d_no}</span>, <span id="g-work-street">${ele.address.street}</span>, <span id="g-work-landmark">${ele.address.landmark}</span></div>
        <div id="id2"><span id="g-work-district">${ele.address.district}</span>, <span id="g-work-state">${ele.address.state}</span></div>
        <div id="id3"><span id="g-work-pincode">${ele.address.pincode}</span>.</div>
    </div>
    <button class="start-work-btn" id="start-work-btn">Start work</button>
</div>`
        }
    }

    for (let i = 0; i < wljson.data.length; i++) {
        let startwork = document.getElementsByClassName('start-work-btn')[i]
        startwork.addEventListener('click', async () => {
            fetch(`http://localhost:8080/work/start?w_id=${wljson.data[i].id}&v_id=${vid}`, {
                method: 'PUT'
            })
            await worksList2()
        })
    }
}
worksList()
async function worksList2() {
    let awb = document.getElementById('awb')
    let owb = document.getElementById('owb')
    let cwb = document.getElementById('cwb')
    document.getElementById('works-list-div1').style.display = 'none'
    document.getElementById('works-list-div2').style.display = 'flex'
    document.getElementById('works-list-div3').style.display = 'none'
    awb.style.borderBottom = 'none'
    owb.style.borderBottom = '2px solid black'
    cwb.style.borderBottom = 'none'

    let wl = await fetch(`http://localhost:8080/ongoingworks?v_id=${localStorage.getItem('id')}`)
    let wljson = await wl.json()
    // console.log(wljson);

    let workdiv = document.getElementById('works-list-div2')
    workdiv.innerHTML = ''

    if (wljson.data.length === 0) {
        let workdiv = document.getElementById('works-list-div2')
        workdiv.innerHTML = `<h2>No works found</h2>`
    } else {
        for (const ele of wljson.data) {
            // console.log(ele);
            // console.log(ele.type);
            let workdiv = document.getElementById('works-list-div2')
            workdiv.innerHTML += `<div id="work-item-div">
    <div id="work-item-div2">
        <h2 id="g-work-name">${ele.type}</h2>
        <p id="g-work-desc">${ele.description}</p>
        <span id="g-work-id">work id : <span id="g-work-id2">${ele.id}</span></span>
    </div>
    <div id="item-address">
        <div id="id1"><span id="g-work-hno">${ele.address.d_no}</span>, <span id="g-work-street">${ele.address.street}</span>, <span id="g-work-landmark">${ele.address.landmark}</span></div>
        <div id="id2"><span id="g-work-district">${ele.address.district}</span>, <span id="g-work-state">${ele.address.state}</span></div>
        <div id="id3"><span id="g-work-pincode">${ele.address.pincode}</span>.</div>
    </div>
    <button class="end-work-btn" id="end-work-btn">End work</button>
</div>`
        }
    }

    for (let i = 0; i < wljson.data.length; i++) {
        let startwork = document.getElementsByClassName('end-work-btn')[i]
        startwork.addEventListener('click', async () => {
            fetch(`http://localhost:8080/work/end?w_id=${wljson.data[i].id}&v_id=${vid}`, {
                method: 'PUT'
            })
            await worksList3()
        })
    }

}
async function worksList3() {
    let awb = document.getElementById('awb')
    let owb = document.getElementById('owb')
    let cwb = document.getElementById('cwb')
    document.getElementById('works-list-div1').style.display = 'none'
    document.getElementById('works-list-div2').style.display = 'none'
    document.getElementById('works-list-div3').style.display = 'flex'
    owb.style.borderBottom = 'none'
    awb.style.borderBottom = 'none'
    cwb.style.borderBottom = '2px solid black'

    let wl = await fetch(`http://localhost:8080/completedworks`)
    let wljson = await wl.json()
    // console.log(wljson);

    let workdiv = document.getElementById('works-list-div3')
    workdiv.innerHTML = ''

    if (wljson.data.length === 0) {
        let workdiv = document.getElementById('works-list-div3')
        workdiv.innerHTML = `<h2>No works completed</h2>`
    } else {
        for (const ele of wljson.data) {
            // console.log(ele);
            // console.log(ele.type);
            let workdiv = document.getElementById('works-list-div3')
            workdiv.innerHTML += `<div id="work-item-div">
    <div id="work-item-div2">
        <h2 id="g-work-name">${ele.type}</h2>
        <p id="g-work-desc">${ele.description}</p>
        <span id="g-work-id">work id : <span id="g-work-id2">${ele.id}</span></span>
    </div>
    <div id="item-address">
        <div id="id1"><span id="g-work-hno">${ele.address.d_no}</span>, <span id="g-work-street">${ele.address.street}</span>, <span id="g-work-landmark">${ele.address.landmark}</span></div>
        <div id="id2"><span id="g-work-district">${ele.address.district}</span>, <span id="g-work-state">${ele.address.state}</span></div>
        <div id="id3"><span id="g-work-pincode">${ele.address.pincode}</span>.</div>
    </div>
    <div>
    <input id="cpd" type="number" placeholder="Cost Per Day">
    <button class="payme-work-btn" id="payme-work-btn">Pay Me</button>
    </div>
</div>`
        }
    }

    for (let i = 0; i < wljson.data.length; i++) {
        let startwork = document.getElementsByClassName('payme-work-btn')[i]
        startwork.addEventListener('click', () => {
            let servicecost = fetch(`http://localhost:8080/serviceCost?v_id=${vid}&w_id=${wljson.data[i].id}`, {
                method: 'POST'
            })
        })
    }

}

// payment -----------------------------------------------------------------------------------------------------





//--------------------------------------------------------------------------------------------------------------
async function districtApiupdate() {
    let inPin = document.getElementById("cus-up-pincode").value;
    if (inPin.length === 6) {
        let dsdiv = document.getElementById("up-ds");
        let spand = document.getElementById("up-ds-d");
        let spans = document.getElementById("up-ds-s");

        try {
            let apidata = await window.fetch(
                "https://api.postalpincode.in/pincode/" + inPin
            );
            let apijson = await apidata.json();
            let status = apijson[0].Status;

            if (status === "Success") {
                dsdiv.style.display = 'flex'
                spand.style.display = 'block'
                spans.style.display = 'block'
                let districtTag = document.getElementById("up-ds-d");
                let stateTag = document.getElementById("up-ds-s");
                var district = apijson[0].PostOffice[0].District;
                var state = apijson[0].PostOffice[0].State;

                districtTag.innerHTML = district
                stateTag.innerHTML = state

                return { district: district, state: state }; // Pin code is valid
            } else {
                dsdiv.style.display = 'flex'
                spand.style.display = "block";
                spans.style.display = "none";
                spand.innerHTML = "Wrong PIN Code";
                spand.style.color = "red";
                return false; // Pin code is invalid
            }
        } catch (error) {
            console.error("Error fetching pin code data:", error);
            return false; // Handle API error
        }
    } else if (inPin.length === 0) {
        let dsdiv = document.getElementById("up-ds");
        dsdiv.style.display = 'none'
        return false;
    } else {
        let dsdiv = document.getElementById("up-ds");
        let spand = document.getElementById("up-ds-d");
        let spans = document.getElementById("up-ds-s");
        spans.style.display = 'none'
        dsdiv.style.display = 'flex'
        spand.innerHTML = "Invalid PIN Code";
        spand.style.color = "red";
        return false; // Invalid pin code format
    }
}


// ---- Update profile --------------------------------------------------------------------------------------------------

let upbtn = document.getElementById('userupdate')
upbtn.addEventListener("click", () => {
    let updiv = document.getElementById('cus-update')
    updiv.style.display = 'block'
})
let cancelupdate = document.getElementById('cancel-up')
cancelupdate.addEventListener("click", () => {
    let updiv = document.getElementById('cus-update')
    updiv.style.display = 'none'
})

// details update-------------------------------------------------------------------------------------------------------

let upsubbtn = document.getElementById('cus-up-btn')
upsubbtn.addEventListener("click", async () => {
    let cusname = document.getElementById('cus-up-name').value
    let cusemail = document.getElementById('cus-up-email').value
    let cusphone = document.getElementById('cus-up-phone').value
    let cuspsw = document.getElementById('cus-up-psw').value
    let role = document.getElementById('cus-up-role').value
    let cusaid = localStorage.getItem("aid")
    let cusdno = document.getElementById('cus-up-dno').value
    let cusstreet = document.getElementById('cus-up-street').value
    let cuslandmark = document.getElementById('cus-up-landmark').value
    let cuspincode = document.getElementById('cus-up-pincode').value
    let ds = await districtApiupdate()
    let cusdistrict = ds.district
    let cusstate = ds.state

    if (cusname === "")
        cusname = localStorage.getItem("name")
    else
        cusname = localStorage.setItem("name", cusname)
    if (cusemail === "")
        cusemail.value = localStorage.getItem("email")
    else
        cusemail = localStorage.setItem("email", cusemail)
    if (cusphone === "")
        cusphone = localStorage.getItem("phone")
    else
        cusphone = localStorage.setItem("phone", cusphone)
    if (role === "")
        role = localStorage.getItem("role")
    else
        cusfc = localStorage.setItem("familycount", cusfc)
    if (cusdno === "")
        cusdno = localStorage.getItem("dno")
    else
        cusdno = localStorage.setItem("dno", cusdno)
    if (cusstreet === "")
        cusstreet = localStorage.getItem("street")
    else
        cusstreet = localStorage.setItem("street", cusstreet)
    if (cuslandmark === "")
        cuslandmark = localStorage.getItem("landmark")
    else
        cuslandmark = localStorage.setItem("landmark", cuslandmark)
    if (cuspincode === "") {
        cuspincode = localStorage.getItem("pincode")
        cusdistrict = localStorage.getItem("district")
        cusstate = localStorage.getItem("state")
    } else {
        cuspincode = localStorage.setItem("pincode", cuspincode)
        cusdistrict = localStorage.setItem("district", cusdistrict)
        cusstate = localStorage.setItem("state", cusstate)
    }

    console.log(email);
    const cusupdateobj = {
        id: vid,
        name: localStorage.getItem("name"),
        email: localStorage.getItem("email"),
        phone: localStorage.getItem("phone"),
        pwd: cuspsw,
        role: localStorage.getItem("role"),
        address: {
            id: cusaid,
            d_no: localStorage.getItem("dno"),
            street: localStorage.getItem("street"),
            landmark: localStorage.getItem("landmark"),
            pincode: localStorage.getItem("pincode"),
            district: localStorage.getItem("district"),
            state: localStorage.getItem("state")
        }
    }

    let cusupdate = JSON.stringify(cusupdateobj)


    // console.log(cusupdate);

    const resp = await fetch('http://localhost:8080/customer', {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: cusupdate,
    })

    let updone = document.getElementById('up-done')
    updone.style.display = 'block'


})