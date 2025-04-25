-- Tasks

-- #1 (10 Points)

-- 1. Create Schema OE.

CREATE SCHEMA oe;

-- 3. Create permanent akses untuk All Object yang ada di schema OE.

SET search_path TO oe;
-- ALTER ROLE postgres SET search_path TO oe; 

-- 2. Create All Objects (Table, Sequence, dll) di script repository.

CREATE TABLE oe.categories(
	category_id SMALLINT PRIMARY KEY,
	category_name VARCHAR(15) NOT NULL,
	description TEXT,
	picture BYTEA
);

CREATE TABLE oe.suppliers(
	supplier_id SMALLINT PRIMARY KEY,
	company_name VARCHAR(40) NOT NULL,
	contact_name VARCHAR(30),
	contact_title VARCHAR(30),
	address VARCHAR(60),
	city VARCHAR(15),
	region VARCHAR(15),
	postal_code VARCHAR(10),
	country VARCHAR(15),
	phone VARCHAR(24),
	fax VARCHAR(24),
	homepage TEXT
);

CREATE TABLE oe.products(
	product_id SMALLINT PRIMARY KEY,
	product_name VARCHAR(40) NOT NULL,
	supplier_id SMALLINT,
	category_id SMALLINT,
	quantity_per_unit VARCHAR(20),
	unit_price REAL,
	units_in_stock SMALLINT,
	units_on_order SMALLINT,
	reorder_level SMALLINT,
	discontinued INTEGER NOT NULL,
	FOREIGN KEY (supplier_id) REFERENCES oe.suppliers (supplier_id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (category_id) REFERENCES oe.categories (category_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE oe.shippers(
	shipper_id SMALLINT PRIMARY KEY,
	company_name VARCHAR(40) NOT NULL,
	phone VARCHAR(24)
);

CREATE TABLE oe.employees(
	employee_id SMALLINT PRIMARY KEY,
	last_name VARCHAR(20) NOT NULL,
	first_name VARCHAR(10) NOT NULL,
	title VARCHAR(30),
	title_of_courtesy VARCHAR(25),
	birth_date DATE,
	hire_date DATE,
	address VARCHAR(60),
	city VARCHAR(15),
	region VARCHAR(15),
	postal_code VARCHAR(10),
	country VARCHAR(15),
	home_phone VARCHAR(24),
	extension VARCHAR(4),
	photo BYTEA,
	notes TEXT,
	reports_to SMALLINT,
	photo_path VARCHAR(255)
);

CREATE TABLE oe.customers(
	customer_id VARCHAR(5) PRIMARY KEY,
	company_name VARCHAR(40) NOT NULL,
	contact_name VARCHAR(30),
	contact_title VARCHAR(30),
	address VARCHAR(60),
	city VARCHAR(15),
	region VARCHAR(15),
	postal_code VARCHAR(10),
	country VARCHAR(15),
	phone VARCHAR(24),
	fax VARCHAR(24)
);

CREATE TABLE oe.orders(
	order_id SMALLINT PRIMARY KEY,
	customer_id VARCHAR(5),
	employee_id SMALLINT,
	order_date DATE,
	required_date DATE,
	shipped_date DATE,
	ship_via SMALLINT,
	freight REAL,
	ship_name VARCHAR(40),
	ship_address VARCHAR(60),
	ship_city VARCHAR(15),
	ship_region VARCHAR(15),
	ship_postal_code VARCHAR(10),
	ship_country VARCHAR(15),
	FOREIGN KEY (employee_id) REFERENCES oe.employees (employee_id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (customer_id) REFERENCES oe.customers (customer_id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (ship_via) REFERENCES oe.shippers(shipper_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE order_details(
	order_id SMALLINT,
	product_id SMALLINT,
	unit_price REAL NOT NULL,
	quantity SMALLINT NOT NULL,
	discount SMALLINT NOT NULL,
	PRIMARY KEY (order_id, product_id),
	FOREIGN KEY (order_id) REFERENCES oe.orders (order_id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (product_id) REFERENCES oe.products (product_id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- #2 (20 Points)

-- 1. Tampilkan data category & total product-nya.

SELECT 
	c.category_id,
	c.category_name,
	count(1) AS total_product
FROM categories c
JOIN products p ON c.category_id = p.category_id
GROUP BY c.category_id, c.category_name
ORDER BY category_name;

-- 2. Tampilkan data supplier & total product-nya

SELECT 
	s.supplier_id,
	s.company_name,
	count(1) AS total_product
FROM suppliers s
JOIN products p ON s.supplier_id = p.supplier_id
GROUP BY s.supplier_id, s.company_name
ORDER BY total_product DESC;

-- 3. Tampilkan data supplier, total product dan harga rata-rata tiap product
--    (gunakan to_char() untuk menampilkan format avg_unit_price)

SELECT 
	s.supplier_id,
	s.company_name,
	count(1) AS total_product,
	TO_CHAR(avg(p.unit_price), 'FM999999.00') avg_unit_price
FROM suppliers s
JOIN products p ON s.supplier_id = p.supplier_id
GROUP BY s.supplier_id, s.company_name
ORDER BY total_product DESC;

-- 4. Tampilkan data product yang harus pesan lagi ke supplier sesuai reorder-level nya.

SELECT 
	p.product_id,
	p.product_name,
	s.supplier_id,
	s.company_name,
	p.unit_price,
	p.units_in_stock,
	p.units_on_order,
	p.reorder_level
FROM suppliers s
JOIN products p ON s.supplier_id = p.supplier_id
WHERE p.units_in_stock <= p.reorder_level
ORDER BY p.product_name;

-- 5. Tampilkan data customer dan total order-nya.

SELECT 
	c.customer_id,
	c.company_name,
	count(1) AS total_order
FROM customers c
JOIN orders o ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.company_name
ORDER BY c.company_name;

-- 6. Tampilkan data order yang melebihi delivery time lebih dari 7 hari. (Belum selesai)

SELECT 
	o.order_id,
	c.customer_id,
	o.order_date,
	o.required_date,
	o.shipped_date,
	(o.shipped_date - o.order_date) AS delivery_time
FROM customers c
JOIN orders o ON c.customer_id = o.customer_id
WHERE (o.shipped_date - o.order_date) > 10 -- 10 NOT 7
ORDER BY o.order_id ASC;

-- 7. Tampilkan total product yang sudah di order dan urut berdasarkan total_quantity terbesar. (Hasil Berbeda) Solved

SELECT 
	p.product_id,
	p.product_name,
	sum(od.quantity) AS total_qty
FROM products p
JOIN order_details od ON p.product_id = od.product_id
JOIN orders o ON od.order_id = o.order_id -- THIS REQUIRED
WHERE o.shipped_date IS NOT NULL -- THIS REQUIRED
GROUP BY p.product_id, p.product_name
ORDER BY total_qty DESC;

-- 8. Tampilkan total product yang sudah di order berdasarkan category. (Hasil Berbeda)

SELECT 
	c.category_id,
	c.category_name,
	sum(od.quantity) AS total_qty_ordered
FROM categories c
JOIN products p ON c.category_id = p.category_id
JOIN order_details od ON p.product_id = od.product_id
GROUP BY c.category_id, c.category_name
ORDER BY total_qty_ordered DESC;

-- 9. Mengacu ke soal no 8, tampilkan data category yang memiliki min & max total_qty_ordered.

WITH product_with_category AS (
	SELECT 
		c.category_id,
		c.category_name,
		sum(od.quantity) AS total_qty_ordered
	FROM categories c
	JOIN products p ON c.category_id = p.category_id
	JOIN order_details od ON p.product_id = od.product_id
	JOIN orders o ON od.order_id = o.order_id -- THIS REQUIRED
	WHERE o.shipped_date IS NOT NULL -- THIS REQUIRED
	GROUP BY c.category_id, c.category_name
	ORDER BY total_qty_ordered DESC
)
SELECT * FROM product_with_category 
WHERE total_qty_ordered = (
	SELECT min(total_qty_ordered) FROM product_with_category
)
OR total_qty_ordered = (
	SELECT max(total_qty_ordered) FROM product_with_category
);

-- 10. Tampilkan data shipper dan total qty product yang dikirim.

SELECT 
	s.shipper_id,
	s.company_name,
	p.product_id,
	p.product_name,
	sum(od.quantity) AS total_qty_ordered
FROM shippers s
JOIN orders o ON s.shipper_id = o.ship_via
JOIN order_details od ON o.order_id = od.order_id
JOIN products p ON od.product_id = p.product_id
GROUP BY s.shipper_id, s.company_name, p.product_id, p.product_name
ORDER BY s.company_name, p.product_name;

-- 11. Tampilkan data shipper dengan product yang paling sering dikirim dan paling minim dikirim.

WITH ctx_total AS (
	SELECT 
		s.shipper_id,
		s.company_name,
		p.product_id,
		p.product_name,
		sum(od.quantity) AS total_qty_ordered
	FROM shippers s
	JOIN orders o ON s.shipper_id = o.ship_via
	JOIN order_details od ON o.order_id = od.order_id
	JOIN products p ON od.product_id = p.product_id
	GROUP BY s.shipper_id, s.company_name, p.product_id, p.product_name
	ORDER BY s.company_name, p.product_name
),
ctx_min_max AS (
	SELECT 
		shipper_id,
		min(total_qty_ordered) AS min_qty,
		max(total_qty_ordered) AS max_qty
	FROM ctx_total
	GROUP BY shipper_id
)
SELECT 
	t.shipper_id,
	t.company_name,
	t.product_id,
	t.product_name,
	t.total_qty_ordered
FROM ctx_total AS t
JOIN ctx_min_max mm ON t.shipper_id = mm.shipper_id
WHERE t.total_qty_ordered = mm.min_qty OR t.total_qty_ordered = mm.max_qty
ORDER BY t.shipper_id, t.total_qty_ordered;

-- 12. Tampilkan hirarki level employee seperti gambar dibawah ini:

SET search_path TO hr;

WITH RECURSIVE hirarki AS (
	SELECT 
		e.employee_id,
		e.first_name || ' ' || e.last_name AS full_name,
		e.manager_id,
		d.department_name,
		1 AS level
	FROM departments d
	JOIN employees e ON d.department_id = e.department_id
	WHERE e.manager_id IS NULL
	
	UNION ALL

	SELECT 
		emp.employee_id,
		emp.first_name || ' ' || emp.last_name AS full_name,
		emp.manager_id,
		dep.department_name,
		h.level + 1
	FROM departments dep
	JOIN employees emp ON dep.department_id = emp.department_id
	JOIN hirarki h ON emp.manager_id = h.employee_id -- IMPORTANT
)
SELECT * FROM hirarki
ORDER BY employee_id;

















