 var seckill = {
    validate : {
        validatePhone : function (phone) {
            if(phone && phone.length == 11 && !isNaN(phone)){
                return true;
            }else {
                return false;
            }
        }
    },
    detail : {
        //详情页初始化
        init : function (params) {
            var killPhone = $.cookie("killPhone");
            var seckillId = params['seckillId'];
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            if(!seckillId.validate.validatePhone(killPhone)){

            }
        }
    }
 }