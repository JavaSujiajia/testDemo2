package com.example.demo.enums;

/**
 * 公用状态，状态枚举可以实现该接口
 * <ul>
 * <li>-1 	删除</li>
 * <li>0	禁用</li>
 * <li>1	启用</li>
 * </ul>
 *
 * @author maheng
 */
public interface Status {

    /**
     * 正常状态		1
     */
    public Status NORMAL = new Status() {
        private Short code = 1;
        private String name = "启用";

        @Override
        public Short getCode() {
            return code;
        }

        @Override
        public String getName() {
            return name;
        }

    };

    /**
     * 禁用状态		-1
     */
    public Status DISABLE = new Status() {
        private Short code = 0;
        private String name = "禁用";

        @Override
        public Short getCode() {
            return code;
        }

        @Override
        public String getName() {
            return name;
        }

    };

    /**
     * 删除状态		-1
     */
    public Status DELETE = new Status() {
        private Short code = -1;
        private String name = "删除";

        @Override
        public Short getCode() {
            return code;
        }

        @Override
        public String getName() {
            return name;
        }

    };


    public Short getCode();

    public String getName();
}
