INSERT INTO DiscountCodeEntity (promoCode, expirationDate, price_amount, price_currency, maximalNumberOfUsage, numberOfUses)
VALUES
    ('BIKE10', '2024-12-31', 10.00, 'USD', 100, 0),
		    ('BIKE20', '2024-12-31', 20.00, 'USD', 200, 0),
		    ('BIKE25EUR', '2024-12-31', 25.00, 'EUR', 150, 0),
		    ('SUMMER15', '2024-08-31', 15.00, 'USD', 5, 0),
		    ('BIKE50', '2024-03-31', 50.00, 'USD', 10, 0);


		INSERT INTO ProductEntity (name, price_amount, price_currency, description)
VALUES
    ('Mountain Bike', 500.00, 'USD', 'A rugged mountain bike designed for off-road adventures.'),
		    ('Road Bike', 750.00, 'USD', 'A sleek road bike optimized for speed on paved roads.'),
		    ('Hybrid Bike', 600.00, 'USD', 'A versatile hybrid bike suitable for city commuting and light trails.'),
		    ('Electric Bike', 1200.00, 'USD', 'An eco-friendly electric bike with pedal assist for effortless riding.'),
		    ('Kids Bike', 150.00, 'USD', 'A fun and colorful bike specially designed for kids.');