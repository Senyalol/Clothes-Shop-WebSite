package com.ClotheShop.CShop.Service;

import com.ClotheShop.CShop.Entity.Product;
import com.ClotheShop.CShop.Repository.ProductRepository;
import com.ClotheShop.CShop.Service.Product.FilterFiles.FilterProducts;
import com.ClotheShop.CShop.Service.Product.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;  // Создаём мок репозитория

    @InjectMocks
    private ProductServiceImpl productService;    // Внедряем мок в сервис

    private Product testProduct1;
    private Product testProduct2;
    private Product testProduct3;

    @BeforeEach
    void setUp() {
        // Создаём тестовые данные перед каждым тестом
        testProduct1 = new Product();
        testProduct1.setId(1);
        testProduct1.setName("Футболка");
        testProduct1.setPrice(1999.99);
        testProduct1.setAmount(10);
        testProduct1.setAvailability(true);
        testProduct1.setColor("Красный");
        testProduct1.setSize(42);
        testProduct1.setSex("Мужской");
        testProduct1.setCategory("Одежда");
        testProduct1.setType("Футболки");
        testProduct1.setRating(100);

        testProduct2 = new Product();
        testProduct2.setId(2);
        testProduct2.setName("Джинсы");
        testProduct2.setPrice(3999.99);
        testProduct2.setAmount(5);
        testProduct2.setAvailability(true);
        testProduct2.setColor("Синий");
        testProduct2.setSize(48);
        testProduct2.setSex("Мужской");
        testProduct2.setCategory("Одежда");
        testProduct2.setType("Джинсы");
        testProduct2.setRating(200);

        testProduct3 = new Product();
        testProduct3.setId(3);
        testProduct3.setName("Платье");
        testProduct3.setPrice(4999.99);
        testProduct3.setAmount(0);
        testProduct3.setAvailability(false);  // Недоступный товар
        testProduct3.setColor("Черный");
        testProduct3.setSize(40);
        testProduct3.setSex("Женский");
        testProduct3.setCategory("Одежда");
        testProduct3.setType("Платья");
        testProduct3.setRating(50);
    }

    // ==================== ТЕСТЫ ДЛЯ addProduct ====================

    @Test
    void addProduct_ShouldSaveProduct_WhenAllChecksPass() {
        // Arrange (Подготовка)
        Product newProduct = new Product();
        newProduct.setName("Куртка");
        newProduct.setPrice(5999.99);
        newProduct.setAmount(3);
        newProduct.setAvailability(true);
        newProduct.setColor("Черный");
        newProduct.setSize(50);
        newProduct.setSex("Мужской");
        newProduct.setCategory("Одежда");
        newProduct.setType("Куртки");

        when(productRepository.save(any(Product.class))).thenReturn(newProduct);

        // Act (Действие)
        Product result = productService.addProduct(newProduct);

        // Assert (Проверка)
        assertNotNull(result);
        assertEquals("Куртка", result.getName());
        verify(productRepository, times(1)).save(newProduct);
    }

    @Test
    void addProduct_ShouldReturnNull_WhenProductIsInvalid() {
        // Arrange - создаём продукт с отсутствующим обязательным полем
        Product invalidProduct = new Product();
        invalidProduct.setName("");  // Пустое имя - должно провалить проверку
        invalidProduct.setPrice(1000.0);
        invalidProduct.setAmount(5);
        invalidProduct.setAvailability(true);
        invalidProduct.setColor("Синий");
        invalidProduct.setSize(44);
        invalidProduct.setSex("Мужской");
        invalidProduct.setCategory("Одежда");
        invalidProduct.setType("Футболки");

        // Act
        Product result = productService.addProduct(invalidProduct);

        // Assert
        assertNull(result);
        verify(productRepository, never()).save(any(Product.class)); // save не должен вызываться
    }

    // ==================== ТЕСТЫ ДЛЯ getAllProducts ====================

    @Test
    void getAllProducts_ShouldReturnOnlyAvailableProducts() {
        // Arrange
        List<Product> allProducts = Arrays.asList(testProduct1, testProduct2, testProduct3);
        when(productRepository.findAll()).thenReturn(allProducts);

        // Act
        List<Product> result = productService.getAllProducts();

        // Assert
        assertEquals(2, result.size()); // Только testProduct1 и testProduct2 (они доступны)
        assertTrue(result.contains(testProduct1));
        assertTrue(result.contains(testProduct2));
        assertFalse(result.contains(testProduct3)); // testProduct3 недоступен
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getAllProducts_ShouldReturnEmptyList_WhenNoAvailableProducts() {
        // Arrange
        testProduct1.setAvailability(false);
        testProduct2.setAvailability(false);
        List<Product> allProducts = Arrays.asList(testProduct1, testProduct2);
        when(productRepository.findAll()).thenReturn(allProducts);

        // Act
        List<Product> result = productService.getAllProducts();

        // Assert
        assertTrue(result.isEmpty());
        verify(productRepository, times(1)).findAll();
    }

    // ==================== ТЕСТЫ ДЛЯ getCertainProduct ====================

    @Test
    void getCertainProduct_ShouldReturnProduct_WhenIdExists() {
        // Arrange
        int productId = 1;
        when(productRepository.findById(productId)).thenReturn(Optional.of(testProduct1));

        // Act
        Product result = productService.getCertainProduct(productId);

        // Assert
        assertNotNull(result);
        assertEquals("Футболка", result.getName());
        assertEquals(1, result.getId());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void getCertainProduct_ShouldThrowException_WhenIdNotFound() {
        // Arrange
        int productId = 999;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(java.util.NoSuchElementException.class,
                () -> productService.getCertainProduct(productId));
        verify(productRepository, times(1)).findById(productId);
    }

    // ==================== ТЕСТЫ ДЛЯ updateProduct ====================

    @Test
    void updateProduct_ShouldUpdateProduct_WhenValidData() {
        // Arrange
        int productId = 1;
        Product updatedProduct = new Product();
        updatedProduct.setName("Обновленная футболка");
        updatedProduct.setPrice(2499.99);
        updatedProduct.setAmount(15);
        updatedProduct.setAvailability(true);
        updatedProduct.setColor("Синий");
        updatedProduct.setSize(44);
        updatedProduct.setSex("мужской");
        updatedProduct.setCategory("Одежда");
        updatedProduct.setType("Футболки");

        when(productRepository.findById(productId)).thenReturn(Optional.of(testProduct1));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        // Act
        Product result = productService.updateProduct(updatedProduct, productId);

        // Assert
        assertNotNull(result);
        verify(productRepository, times(2)).findById(productId); // вызывается дважды в методе
        verify(productRepository, never()).save(any()); // в вашем коде save не вызывается напрямую
    }

    // ==================== ТЕСТЫ ДЛЯ deleteProduct ====================

    @Test
    void deleteProduct_ShouldDeleteProduct_WhenIdExists() {
        // Arrange
        int productId = 1;
        when(productRepository.findById(productId)).thenReturn(Optional.of(testProduct1));
        doNothing().when(productRepository).deleteById(productId);

        // Act
        productService.deleteProduct(productId);

        // Assert
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }

    // ==================== ТЕСТЫ ДЛЯ filterProduct ====================

    @Test
    void filterProduct_ShouldReturnFilteredProducts_WhenFiltersApplied() {
        // Arrange
        List<Product> allProducts = Arrays.asList(testProduct1, testProduct2, testProduct3);
        when(productRepository.findAll()).thenReturn(allProducts);

        FilterProducts filters = new FilterProducts();
        filters.setColor("Красный");
        filters.setSex("Мужской");

        // Используем рефлексию для установки полей фильтра, если нет сеттеров
        // Или предполагаем, что у FilterProducts есть сеттеры

        // Act
        List<Product> result = productService.filterProduct(filters);

        // Assert
        assertEquals(1, result.size());
        assertEquals("Футболка", result.get(0).getName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void filterProduct_ShouldReturnAllProducts_WhenNoFiltersApplied() {
        // Arrange
        List<Product> allProducts = Arrays.asList(testProduct1, testProduct2, testProduct3);
        when(productRepository.findAll()).thenReturn(allProducts);

        FilterProducts filters = new FilterProducts(); // все поля null

        // Act
        List<Product> result = productService.filterProduct(filters);

        // Assert
        assertEquals(3, result.size());
        verify(productRepository, times(1)).findAll();
    }

    // ==================== ТЕСТЫ ДЛЯ findByName ====================

    @Test
    void findByName_ShouldReturnProducts_WhenNameExists() {
        // Arrange
        String searchName = "Футболка";
        List<Product> expectedProducts = Arrays.asList(testProduct1);
        when(productRepository.findByName(searchName)).thenReturn(expectedProducts);

        // Act
        List<Product> result = productService.findByName(searchName);

        // Assert
        assertEquals(1, result.size());
        assertEquals("Футболка", result.get(0).getName());
        verify(productRepository, times(1)).findByName(searchName);
    }

    @Test
    void findByName_ShouldReturnEmptyList_WhenNameNotFound() {
        // Arrange
        String searchName = "Несуществующий товар";
        when(productRepository.findByName(searchName)).thenReturn(Arrays.asList());

        // Act
        List<Product> result = productService.findByName(searchName);

        // Assert
        assertTrue(result.isEmpty());
        verify(productRepository, times(1)).findByName(searchName);
    }

    // ==================== ТЕСТЫ ДЛЯ popularProducts ====================

    @Test
    void popularProducts_ShouldReturnTop6ProductsByRating() {
        // Arrange
        // Создадим 8 продуктов с разными рейтингами
        Product p1 = createProductWithRating(1, 100);
        Product p2 = createProductWithRating(2, 200);
        Product p3 = createProductWithRating(3, 300);
        Product p4 = createProductWithRating(4, 400);
        Product p5 = createProductWithRating(5, 500);
        Product p6 = createProductWithRating(6, 600);
        Product p7 = createProductWithRating(7, 700);
        Product p8 = createProductWithRating(8, 800);

        List<Product> allProducts = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8);
        when(productRepository.findAll()).thenReturn(allProducts);

        // Act
        List<Product> result = productService.popularProducts();

        // Assert
        assertEquals(6, result.size());
        // Проверяем, что первые 6 - с наивысшим рейтингом (800, 700, 600, 500, 400, 300)
        assertEquals(800, result.get(0).getRating());
        assertEquals(700, result.get(1).getRating());
        assertEquals(600, result.get(2).getRating());
        assertEquals(500, result.get(3).getRating());
        assertEquals(400, result.get(4).getRating());
        assertEquals(300, result.get(5).getRating());
    }

    @Test
    void popularProducts_ShouldReturnAllProducts_WhenLessThan6Products() {
        // Arrange
        List<Product> allProducts = Arrays.asList(testProduct1, testProduct2);
        when(productRepository.findAll()).thenReturn(allProducts);

        // Act
        List<Product> result = productService.popularProducts();

        // Assert
        assertEquals(2, result.size());
        assertEquals(200, result.get(0).getRating()); // testProduct2 с рейтингом 200
        assertEquals(100, result.get(1).getRating()); // testProduct1 с рейтингом 100
    }

    // Вспомогательный метод для создания продуктов с рейтингом
    private Product createProductWithRating(int id, int rating) {
        Product product = new Product();
        product.setId(id);
        product.setName("Product " + id);
        product.setRating(rating);
        product.setAvailability(true);
        return product;
    }
}