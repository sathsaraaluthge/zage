/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80046 (8.0.46)
 Source Host           : localhost:3306
 Source Schema         : zage_groom

 Target Server Type    : MySQL
 Target Server Version : 80046 (8.0.46)
 File Encoding         : 65001

 Date: 15/07/2026 20:00:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for categories
-- ----------------------------
DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `description` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of categories
-- ----------------------------

-- ----------------------------
-- Table structure for colors
-- ----------------------------
DROP TABLE IF EXISTS `colors`;
CREATE TABLE `colors`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `hex_code` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of colors
-- ----------------------------

-- ----------------------------
-- Table structure for custom_order_options
-- ----------------------------
DROP TABLE IF EXISTS `custom_order_options`;
CREATE TABLE `custom_order_options`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `custom_orders_id` int NOT NULL,
  `custom_orders_products_id` int NOT NULL,
  `custom_orders_products_categories_id` int NOT NULL,
  `custom_orders_measurement_profiles_id` int NOT NULL,
  `custom_orders_measurement_profiles_customers_id` int NOT NULL,
  `custom_orders_measurement_profiles_customers_users_id` int NOT NULL,
  `customization_options_id` int NOT NULL,
  `customization_values_id` int NOT NULL,
  `customization_values_customization_options_id` int NOT NULL,
  PRIMARY KEY (`id`, `custom_orders_id`, `custom_orders_products_id`, `custom_orders_products_categories_id`, `custom_orders_measurement_profiles_id`, `custom_orders_measurement_profiles_customers_id`, `custom_orders_measurement_profiles_customers_users_id`, `customization_options_id`, `customization_values_id`, `customization_values_customization_options_id`) USING BTREE,
  INDEX `fk_custom_order_options_custom_orders1_idx`(`custom_orders_id` ASC, `custom_orders_products_id` ASC, `custom_orders_products_categories_id` ASC, `custom_orders_measurement_profiles_id` ASC, `custom_orders_measurement_profiles_customers_id` ASC, `custom_orders_measurement_profiles_customers_users_id` ASC) USING BTREE,
  INDEX `fk_custom_order_options_customization_options1_idx`(`customization_options_id` ASC) USING BTREE,
  INDEX `fk_custom_order_options_customization_values1_idx`(`customization_values_id` ASC, `customization_values_customization_options_id` ASC) USING BTREE,
  CONSTRAINT `fk_custom_order_options_custom_orders1` FOREIGN KEY (`custom_orders_id`, `custom_orders_products_id`, `custom_orders_products_categories_id`, `custom_orders_measurement_profiles_id`, `custom_orders_measurement_profiles_customers_id`, `custom_orders_measurement_profiles_customers_users_id`) REFERENCES `custom_orders` (`id`, `products_id`, `products_categories_id`, `measurement_profiles_id`, `measurement_profiles_customers_id`, `measurement_profiles_customers_users_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_custom_order_options_customization_options1` FOREIGN KEY (`customization_options_id`) REFERENCES `customization_options` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_custom_order_options_customization_values1` FOREIGN KEY (`customization_values_id`, `customization_values_customization_options_id`) REFERENCES `customization_values` (`id`, `customization_options_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of custom_order_options
-- ----------------------------

-- ----------------------------
-- Table structure for custom_orders
-- ----------------------------
DROP TABLE IF EXISTS `custom_orders`;
CREATE TABLE `custom_orders`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` enum('ACTIVE','INACTIVE') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `notes` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `products_id` int NOT NULL,
  `products_categories_id` int NOT NULL,
  `measurement_profiles_id` int NOT NULL,
  `measurement_profiles_customers_id` int NOT NULL,
  `measurement_profiles_customers_users_id` int NOT NULL,
  PRIMARY KEY (`id`, `products_id`, `products_categories_id`, `measurement_profiles_id`, `measurement_profiles_customers_id`, `measurement_profiles_customers_users_id`) USING BTREE,
  INDEX `fk_custom_orders_products1_idx`(`products_id` ASC, `products_categories_id` ASC) USING BTREE,
  INDEX `fk_custom_orders_measurement_profiles1_idx`(`measurement_profiles_id` ASC, `measurement_profiles_customers_id` ASC, `measurement_profiles_customers_users_id` ASC) USING BTREE,
  CONSTRAINT `fk_custom_orders_measurement_profiles1` FOREIGN KEY (`measurement_profiles_id`, `measurement_profiles_customers_id`, `measurement_profiles_customers_users_id`) REFERENCES `measurement_profiles` (`id`, `customers_id`, `customers_users_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_custom_orders_products1` FOREIGN KEY (`products_id`, `products_categories_id`) REFERENCES `products` (`id`, `categories_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of custom_orders
-- ----------------------------

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `date_of_birth` date NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `users_id` int NOT NULL,
  PRIMARY KEY (`id`, `users_id`) USING BTREE,
  INDEX `fk_customers_users_idx`(`users_id` ASC) USING BTREE,
  CONSTRAINT `fk_customers_users` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customers
-- ----------------------------

-- ----------------------------
-- Table structure for customization_options
-- ----------------------------
DROP TABLE IF EXISTS `customization_options`;
CREATE TABLE `customization_options`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `type` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customization_options
-- ----------------------------

-- ----------------------------
-- Table structure for customization_values
-- ----------------------------
DROP TABLE IF EXISTS `customization_values`;
CREATE TABLE `customization_values`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `value` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `customization_options_id` int NOT NULL,
  PRIMARY KEY (`id`, `customization_options_id`) USING BTREE,
  INDEX `fk_customization_values_customization_options1_idx`(`customization_options_id` ASC) USING BTREE,
  CONSTRAINT `fk_customization_values_customization_options1` FOREIGN KEY (`customization_options_id`) REFERENCES `customization_options` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customization_values
-- ----------------------------

-- ----------------------------
-- Table structure for inventory
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantity` int NULL DEFAULT NULL,
  `location` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `product_variants_id` int NOT NULL,
  `product_variants_products_id` int NOT NULL,
  `product_variants_products_categories_id` int NOT NULL,
  `product_variants_colors_id` int NOT NULL,
  `product_variants_materials_id` int NOT NULL,
  `product_variants_sizes_id` int NOT NULL,
  PRIMARY KEY (`id`, `product_variants_id`, `product_variants_products_id`, `product_variants_products_categories_id`, `product_variants_colors_id`, `product_variants_materials_id`, `product_variants_sizes_id`) USING BTREE,
  INDEX `fk_inventory_product_variants1_idx`(`product_variants_id` ASC, `product_variants_products_id` ASC, `product_variants_products_categories_id` ASC, `product_variants_colors_id` ASC, `product_variants_materials_id` ASC, `product_variants_sizes_id` ASC) USING BTREE,
  CONSTRAINT `fk_inventory_product_variants1` FOREIGN KEY (`product_variants_id`, `product_variants_products_id`, `product_variants_products_categories_id`, `product_variants_colors_id`, `product_variants_materials_id`, `product_variants_sizes_id`) REFERENCES `product_variants` (`id`, `products_id`, `products_categories_id`, `colors_id`, `materials_id`, `sizes_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inventory
-- ----------------------------

-- ----------------------------
-- Table structure for materials
-- ----------------------------
DROP TABLE IF EXISTS `materials`;
CREATE TABLE `materials`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `description` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of materials
-- ----------------------------

-- ----------------------------
-- Table structure for measurement_profiles
-- ----------------------------
DROP TABLE IF EXISTS `measurement_profiles`;
CREATE TABLE `measurement_profiles`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `is_default` tinyint NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `customers_id` int NOT NULL,
  `customers_users_id` int NOT NULL,
  PRIMARY KEY (`id`, `customers_id`, `customers_users_id`) USING BTREE,
  INDEX `fk_measurement_profiles_customers1_idx`(`customers_id` ASC, `customers_users_id` ASC) USING BTREE,
  CONSTRAINT `fk_measurement_profiles_customers1` FOREIGN KEY (`customers_id`, `customers_users_id`) REFERENCES `customers` (`id`, `users_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of measurement_profiles
-- ----------------------------

-- ----------------------------
-- Table structure for measurement_types
-- ----------------------------
DROP TABLE IF EXISTS `measurement_types`;
CREATE TABLE `measurement_types`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `category` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `unit` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of measurement_types
-- ----------------------------

-- ----------------------------
-- Table structure for measurement_values
-- ----------------------------
DROP TABLE IF EXISTS `measurement_values`;
CREATE TABLE `measurement_values`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(10, 0) NULL DEFAULT NULL,
  `unit` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `measurement_profiles_id` int NOT NULL,
  `measurement_profiles_customers_id` int NOT NULL,
  `measurement_profiles_customers_users_id` int NOT NULL,
  `measurement_types_id` int NOT NULL,
  PRIMARY KEY (`id`, `measurement_profiles_id`, `measurement_profiles_customers_id`, `measurement_profiles_customers_users_id`, `measurement_types_id`) USING BTREE,
  INDEX `fk_measurement_values_measurement_profiles1_idx`(`measurement_profiles_id` ASC, `measurement_profiles_customers_id` ASC, `measurement_profiles_customers_users_id` ASC) USING BTREE,
  INDEX `fk_measurement_values_measurement_types1_idx`(`measurement_types_id` ASC) USING BTREE,
  CONSTRAINT `fk_measurement_values_measurement_profiles1` FOREIGN KEY (`measurement_profiles_id`, `measurement_profiles_customers_id`, `measurement_profiles_customers_users_id`) REFERENCES `measurement_profiles` (`id`, `customers_id`, `customers_users_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_measurement_values_measurement_types1` FOREIGN KEY (`measurement_types_id`) REFERENCES `measurement_types` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of measurement_values
-- ----------------------------

-- ----------------------------
-- Table structure for order_items
-- ----------------------------
DROP TABLE IF EXISTS `order_items`;
CREATE TABLE `order_items`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantity` int NULL DEFAULT NULL,
  `price` decimal(10, 0) NULL DEFAULT NULL,
  `orders_id` int NOT NULL,
  `orders_customers_id` int NOT NULL,
  `orders_customers_users_id` int NOT NULL,
  `products_id` int NOT NULL,
  `products_categories_id` int NOT NULL,
  `product_variants_id` int NOT NULL,
  `product_variants_products_id` int NOT NULL,
  `product_variants_products_categories_id` int NOT NULL,
  `product_variants_colors_id` int NOT NULL,
  `product_variants_materials_id` int NOT NULL,
  `product_variants_sizes_id` int NOT NULL,
  PRIMARY KEY (`id`, `orders_id`, `orders_customers_id`, `orders_customers_users_id`, `products_id`, `products_categories_id`, `product_variants_id`, `product_variants_products_id`, `product_variants_products_categories_id`, `product_variants_colors_id`, `product_variants_materials_id`, `product_variants_sizes_id`) USING BTREE,
  INDEX `fk_order_items_orders1_idx`(`orders_id` ASC, `orders_customers_id` ASC, `orders_customers_users_id` ASC) USING BTREE,
  INDEX `fk_order_items_products1_idx`(`products_id` ASC, `products_categories_id` ASC) USING BTREE,
  INDEX `fk_order_items_product_variants1_idx`(`product_variants_id` ASC, `product_variants_products_id` ASC, `product_variants_products_categories_id` ASC, `product_variants_colors_id` ASC, `product_variants_materials_id` ASC, `product_variants_sizes_id` ASC) USING BTREE,
  CONSTRAINT `fk_order_items_orders1` FOREIGN KEY (`orders_id`, `orders_customers_id`, `orders_customers_users_id`) REFERENCES `orders` (`id`, `customers_id`, `customers_users_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_order_items_product_variants1` FOREIGN KEY (`product_variants_id`, `product_variants_products_id`, `product_variants_products_categories_id`, `product_variants_colors_id`, `product_variants_materials_id`, `product_variants_sizes_id`) REFERENCES `product_variants` (`id`, `products_id`, `products_categories_id`, `colors_id`, `materials_id`, `sizes_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_order_items_products1` FOREIGN KEY (`products_id`, `products_categories_id`) REFERENCES `products` (`id`, `categories_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_items
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_type` enum('READY_MADE','CUSTOM','RENTAL') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `total_amount` decimal(10, 0) NULL DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `customers_id` int NOT NULL,
  `customers_users_id` int NOT NULL,
  PRIMARY KEY (`id`, `customers_id`, `customers_users_id`) USING BTREE,
  INDEX `fk_orders_customers1_idx`(`customers_id` ASC, `customers_users_id` ASC) USING BTREE,
  CONSTRAINT `fk_orders_customers1` FOREIGN KEY (`customers_id`, `customers_users_id`) REFERENCES `customers` (`id`, `users_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for package_items
-- ----------------------------
DROP TABLE IF EXISTS `package_items`;
CREATE TABLE `package_items`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantity` int NULL DEFAULT NULL,
  `wedding_packages_id` int NOT NULL,
  `products_id` int NOT NULL,
  `products_categories_id` int NOT NULL,
  PRIMARY KEY (`id`, `wedding_packages_id`, `products_id`, `products_categories_id`) USING BTREE,
  INDEX `fk_package_items_wedding_packages1_idx`(`wedding_packages_id` ASC) USING BTREE,
  INDEX `fk_package_items_products1_idx`(`products_id` ASC, `products_categories_id` ASC) USING BTREE,
  CONSTRAINT `fk_package_items_products1` FOREIGN KEY (`products_id`, `products_categories_id`) REFERENCES `products` (`id`, `categories_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_package_items_wedding_packages1` FOREIGN KEY (`wedding_packages_id`) REFERENCES `wedding_packages` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of package_items
-- ----------------------------

-- ----------------------------
-- Table structure for payments
-- ----------------------------
DROP TABLE IF EXISTS `payments`;
CREATE TABLE `payments`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` decimal(10, 0) NULL DEFAULT NULL,
  `method` enum('ACTIVE','INACTIVE') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `payment_date` timestamp NULL DEFAULT NULL,
  `orders_id` int NOT NULL,
  `orders_customers_id` int NOT NULL,
  `orders_customers_users_id` int NOT NULL,
  PRIMARY KEY (`id`, `orders_id`, `orders_customers_id`, `orders_customers_users_id`) USING BTREE,
  INDEX `fk_payments_orders1_idx`(`orders_id` ASC, `orders_customers_id` ASC, `orders_customers_users_id` ASC) USING BTREE,
  CONSTRAINT `fk_payments_orders1` FOREIGN KEY (`orders_id`, `orders_customers_id`, `orders_customers_users_id`) REFERENCES `orders` (`id`, `customers_id`, `customers_users_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payments
-- ----------------------------

-- ----------------------------
-- Table structure for product_images
-- ----------------------------
DROP TABLE IF EXISTS `product_images`;
CREATE TABLE `product_images`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `image_url` varchar(450) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `type` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `products_id` int NOT NULL,
  `products_categories_id` int NOT NULL,
  PRIMARY KEY (`id`, `products_id`, `products_categories_id`) USING BTREE,
  INDEX `fk_product_images_products1_idx`(`products_id` ASC, `products_categories_id` ASC) USING BTREE,
  CONSTRAINT `fk_product_images_products1` FOREIGN KEY (`products_id`, `products_categories_id`) REFERENCES `products` (`id`, `categories_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_images
-- ----------------------------

-- ----------------------------
-- Table structure for product_variants
-- ----------------------------
DROP TABLE IF EXISTS `product_variants`;
CREATE TABLE `product_variants`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `sku` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 0) NULL DEFAULT NULL,
  `stock_qty` decimal(10, 0) NULL DEFAULT NULL,
  `products_id` int NOT NULL,
  `products_categories_id` int NOT NULL,
  `colors_id` int NOT NULL,
  `materials_id` int NOT NULL,
  `sizes_id` int NOT NULL,
  PRIMARY KEY (`id`, `products_id`, `products_categories_id`, `colors_id`, `materials_id`, `sizes_id`) USING BTREE,
  INDEX `fk_product_variants_products1_idx`(`products_id` ASC, `products_categories_id` ASC) USING BTREE,
  INDEX `fk_product_variants_colors1_idx`(`colors_id` ASC) USING BTREE,
  INDEX `fk_product_variants_materials1_idx`(`materials_id` ASC) USING BTREE,
  INDEX `fk_product_variants_sizes1_idx`(`sizes_id` ASC) USING BTREE,
  CONSTRAINT `fk_product_variants_colors1` FOREIGN KEY (`colors_id`) REFERENCES `colors` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_product_variants_materials1` FOREIGN KEY (`materials_id`) REFERENCES `materials` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_product_variants_products1` FOREIGN KEY (`products_id`, `products_categories_id`) REFERENCES `products` (`id`, `categories_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_product_variants_sizes1` FOREIGN KEY (`sizes_id`) REFERENCES `sizes` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_variants
-- ----------------------------

-- ----------------------------
-- Table structure for production_orders
-- ----------------------------
DROP TABLE IF EXISTS `production_orders`;
CREATE TABLE `production_orders`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `stage` enum('CUTTING','STITCHING','QUALITY_CHECK','READY','DELIVERED') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `custom_orders_id` int NOT NULL,
  `custom_orders_products_id` int NOT NULL,
  `custom_orders_products_categories_id` int NOT NULL,
  `custom_orders_measurement_profiles_id` int NOT NULL,
  `custom_orders_measurement_profiles_customers_id` int NOT NULL,
  `custom_orders_measurement_profiles_customers_users_id` int NOT NULL,
  PRIMARY KEY (`id`, `custom_orders_id`, `custom_orders_products_id`, `custom_orders_products_categories_id`, `custom_orders_measurement_profiles_id`, `custom_orders_measurement_profiles_customers_id`, `custom_orders_measurement_profiles_customers_users_id`) USING BTREE,
  INDEX `fk_production_orders_custom_orders1_idx`(`custom_orders_id` ASC, `custom_orders_products_id` ASC, `custom_orders_products_categories_id` ASC, `custom_orders_measurement_profiles_id` ASC, `custom_orders_measurement_profiles_customers_id` ASC, `custom_orders_measurement_profiles_customers_users_id` ASC) USING BTREE,
  CONSTRAINT `fk_production_orders_custom_orders1` FOREIGN KEY (`custom_orders_id`, `custom_orders_products_id`, `custom_orders_products_categories_id`, `custom_orders_measurement_profiles_id`, `custom_orders_measurement_profiles_customers_id`, `custom_orders_measurement_profiles_customers_users_id`) REFERENCES `custom_orders` (`id`, `products_id`, `products_categories_id`, `measurement_profiles_id`, `measurement_profiles_customers_id`, `measurement_profiles_customers_users_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of production_orders
-- ----------------------------

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `product_type` enum('READY_MADE','CUSTOM','RENTAL') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `is_customizable` tinyint NULL DEFAULT NULL,
  `selling_price` decimal(10, 0) NULL DEFAULT NULL,
  `rental_price` decimal(10, 0) NULL DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `categories_id` int NOT NULL,
  PRIMARY KEY (`id`, `categories_id`) USING BTREE,
  INDEX `fk_products_categories1_idx`(`categories_id` ASC) USING BTREE,
  CONSTRAINT `fk_products_categories1` FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------

-- ----------------------------
-- Table structure for sizes
-- ----------------------------
DROP TABLE IF EXISTS `sizes`;
CREATE TABLE `sizes`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `type` enum('S','M','L','XL','42','44') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sizes
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `phone` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `password` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `role` enum('ADMIN','STAFF','CUSTOMER') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------

-- ----------------------------
-- Table structure for wedding_packages
-- ----------------------------
DROP TABLE IF EXISTS `wedding_packages`;
CREATE TABLE `wedding_packages`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 0) NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wedding_packages
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
