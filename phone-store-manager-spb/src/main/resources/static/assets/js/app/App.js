class App {
    static DOMAIN_SERVER = "http://localhost:9000";
    static STAFF_API = this.DOMAIN_SERVER + "/api/staffs";
    static USER_API = this.DOMAIN_SERVER + "/api/users";
    static AUTH_URL = this.DOMAIN_SERVER + "/api/auth";
    static ROLE_API = this.DOMAIN_SERVER + "/api/roles";
    static PRODUCT_API = this.DOMAIN_SERVER + "/api/products";
    static CUSTOMER_API = this.DOMAIN_SERVER + "/api/customers";
    static ERROR_URL = this.DOMAIN_SERVER + "/error/";
    static PROVINCE_URL = "https://vapi.vnappmob.com/api/province/";

    static BASE_URL_CLOUD_IMAGE = "https://res.cloudinary.com/dg4kw5uuy/image/upload";
    static SCALE_IMAGE_W100_H80_Q100 = "c_limit,w_100,h_80,q_100";
    static SCALE_IMAGE_W600_H650_Q100 = "c_limit,w_600,h_650,q_100";
    static SCALE_IMAGE_W150_H100_Q100 = "c_limit,w_150,h_100,q_100";
    static SCALE_IMAGE_W200_H250_Q100 = "c_limit,w_200,h_250,q_100";

    static SweetAlert = class {
        static showDeactivateConfirmDialog() {
            return Swal.fire({
                icon: 'warning',
                text: 'Are you sure to deactivate the selected customer ?',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, please deactivate this client !',
                cancelButtonText: 'Cancel',
            })
        }

        static showSuccessAlert(t) {
            Swal.fire({
                icon: 'success',
                title: t,
                position: 'top-end',
                showConfirmButton: false,
                timer: 1500
            })
        }

        static showErrorAlert(t) {
            Swal.fire({
                icon: 'error',
                title: 'Warning',
                text: t,
            })
        }

        static showError401() {
            Swal.fire({
                icon: 'error',
                title: 'Access Denied',
                text: 'Invalid credentials!',
            })
        }

        static showError403() {
            Swal.fire({
                icon: 'error',
                title: 'Access Denied',
                text: 'Bạn không được phép thực hiện chức năng này!',
            })
        }

        static showError500() {
            Swal.fire({
                icon: 'error',
                title: 'Internal Server Error',
                text: 'Hệ thống Server đang có vấn đề hoặc không truy cập được.',
            })
        }

        static redirectPage(message1, message2, timer) {
            let timerInterval;
            Swal.fire({
                icon: 'success',
                title: "<br>" + message1,
                html: message2,
                timer: timer,
                timerProgressBar: true,
                didOpen: () => {
                    Swal.showLoading()
                    const b = Swal.getHtmlContainer().querySelector('b')
                    timerInterval = setInterval(() => {
                        b.textContent = Swal.getTimerLeft()
                    }, 100)
                },
                willClose: () => {
                    clearInterval(timerInterval);
                }
            }).then((result) => {
                if (result.dismiss === Swal.DismissReason.timer) {
                    console.log('I was closed by the timer')
                }
            })
        }

    }

    static IziToast = class {
        static showSuccessAlertLeft(m) {
            iziToast.success({
                title: 'OK',
                position: 'topLeft',
                timeout: 2500,
                message: m
            });
        }

        static showSuccessAlertRight(m) {
            iziToast.success({
                title: 'OK',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }

        static showErrorAlertLeft(m) {
            iziToast.error({
                title: 'Error',
                position: 'topLeft',
                timeout: 2500,
                message: m
            });
        }

        static showErrorAlertRight(m) {
            iziToast.error({
                title: 'Error',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }

    }
}

class LocationRegion {
    constructor(id, provinceId, provinceName, districtId, districtName, wardId, wardName, address) {
        this.id = id;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.wardId = wardId;
        this.wardName = wardName;
        this.address = address;
    }
}

class Role {
    constructor(id, code) {
        this.id = id;
        this.code = code;
    }
}

class User {
    constructor(id, username, password, role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

class StaffAvatar {
    constructor(id, fileName, fileFolder, fileUrl, fileType, cloudId, ts) {
        this.id = id;
        this.fileName = fileName;
        this.fileFolder = fileFolder;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
        this.cloudId = cloudId;
        this.ts = ts;
    }
}

class Staff {
    constructor(id, fullName, phone, user, locationRegion, staffAvatar) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.user = user;
        this.locationRegion = locationRegion;
        this.staffAvatar = staffAvatar;
    }
}

class ProductAvatar {
    constructor(id, fileName, fileFolder, fileUrl, fileType, cloudId, ts) {
        this.id = id;
        this.fileName = fileName;
        this.fileFolder = fileFolder;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
        this.cloudId = cloudId;
        this.ts = ts;
    }
}

class Product {
    constructor(id, productName, price, quantity, description, productAvatar) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.productAvatar = productAvatar;
    }
}

class CustomerAvatar {
    constructor(id, fileName, fileFolder, fileUrl, fileType, cloudId, ts) {
        this.id = id;
        this.fileName = fileName;
        this.fileFolder = fileFolder;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
        this.cloudId = cloudId;
        this.ts = ts;
    }
}

class Customer {
    constructor(id, fullName, phone, user, locationRegion, customerAvatar) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.user = user;
        this.locationRegion = locationRegion;
        this.customerAvatar = customerAvatar;
    }
}