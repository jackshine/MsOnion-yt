/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营品牌洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际品牌直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */


package cc.msonion.carambola.parent.ext.common;

/**
 * @Title: MsOnionPojoStringFieldUtils.java
 * @Package: cc.msonion.carambola.parent.ext.common
 * @Description: pojo字符串出空格
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月08日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年04月08日
 * @Modify-version: V2.0.0
 * @Modify-description: pojo 字符串去空格方法
 */

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionReflectionUtils;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/** pojo字符串工具类
 * @ClassName: MsOnionPojoStringFieldUtils
 * @Description: pojo字符串工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月08日
 *
 */
public final  class MsOnionPojoStringFieldUtils {

    private MsOnionPojoStringFieldUtils() {
    }

    /**
     * pojo字符串去空格方法，便于hibernate validator校验长度
     * @param pojo MsOnionBasePojoAdapter
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException 异常
     */
    public static void inspectPojoStringFieldValue(MsOnionBasePojoAdapter pojo) throws MsOnionIllegalArgumentException, MsOnionException {

        MsOnionReflectionUtils.doWithFields(pojo.getClass(), new org.springframework.util.ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                    // 允许访问
                    field.setAccessible(true);
                    String name = field.getName();
                    Object value = field.get(pojo);
                    String typeName = field.getType().getName();

                    // 字符串类型 String
                    if ("java.lang.String".equals(typeName)) {
                        if (value != null && StringUtils.isNotBlank(value + "")) {
                            // 去掉左右两边空格之后，设置值给Field
                            field.set(pojo, value.toString().trim());
                        }
                    }
            }
        });
    }


}
